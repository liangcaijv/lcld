package org.lanqiao.sql.ijdbc.impl;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.google.common.reflect.Reflection;
import org.lanqiao.sql.ijdbc.exception.UnImplementionException;

/**
 * 简易连接池实现类<br>
 * DataSource应该是单实例的,一个系统只需要一个数据源;<br>
 * DataSource的主要作用是提供数据库连接,此处提供的连接实例实际上是Connection对象的一个代理对象<br>
 * 主要针对Connection的close方法做替代,不是直接关闭连接,而是回收到一个队列中,供重复使用<br>
 * 
 * @author zhengwei
 * 
 */
enum SimpleDataSource implements DataSource {
  me;
  // 阻塞队列，应对多线程请求连接
  private BlockingQueue<Connection> pool = new LinkedBlockingQueue<Connection>();

  private SimpleDataSource() {
    try {
      Class.forName(Config.getDriver());
      //初始化固定数量的连接到连接池
      for (int i = 0; i < Config.getPoolSize(); i++) {
        // 创建原始的connection
        Connection conn = DriverManager.getConnection(Config.getUrl(),
            Config.getName(), Config.getPwd());
        //        创建代理对象
        Connection connProxy = ConnectionProxy.getProxy(conn, pool);// 获取代理的对象
        // 添加/缓存代理对象
        pool.add(connProxy);
      }
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  /** 不支持日志操作 */
  public PrintWriter getLogWriter() throws SQLException {
    throw new UnImplementionException();
  }

  public void setLogWriter(PrintWriter out) throws SQLException {
    throw new UnImplementionException();
  }

  /** 不支持超时操作 */
  public void setLoginTimeout(int seconds) throws SQLException {
    throw new UnImplementionException();
  }

  public int getLoginTimeout() throws SQLException {
    return 0;
  }

  @SuppressWarnings("unchecked")
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return (T) this;
  }

  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return DataSource.class.equals(iface);
  }

  /** 从池中取一个连接对象 */
  public Connection getConnection() throws SQLException {
    try {
      return pool.take();//提取并移除队列中的一个对象,是一个代理对象
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return null;
  }

  public Connection getConnection(String username, String password)
      throws SQLException {
    throw new UnImplementionException();
  }

  /** 实现对Connection的动态代理 */
  private static class ConnectionProxy implements InvocationHandler {

    //原始连接
    private Connection conn;
    private BlockingQueue<Connection> pool;

    private ConnectionProxy(Connection conn, BlockingQueue<Connection> pool) {
      this.conn = conn;
      this.pool = pool;
    }

    /**
     * 返回代理连接
     * 
     * @param o
     * @param pool
     * @return
     */
    public static Connection getProxy(Object o, BlockingQueue<Connection> pool) {
      /*Object proxed = Proxy.newProxyInstance(o.getClass().getClassLoader(),
          new Class[] { Connection.class }, new ConnectionProxy((Connection) o,
              pool));
      return (Connection) proxed;*/
      return Reflection.newProxy(Connection.class,new ConnectionProxy((Connection) o,pool));
    }

    /**
     * 原始对象上的方法被调用时,会调此方法
     */
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
      // 实际上handler能代理目标对象上的所有方法，即所有方法的调用都会到这里来
      //      但是我们只改变close方法的行为
      if (method.getName().equals("close")) {
        pool.put((Connection) proxy);//回收代理对象，原生对象不做任何处理
        return null;//本来close就没有返回值
      } else { // close之外的其他方法
      //        直接返回其方法调用即可
        return method.invoke(conn, args);
      }
    }

  }

  @Override
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    throw new UnImplementionException();
  }

}

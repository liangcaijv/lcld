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

import org.lanqiao.sql.ijdbc.exception.UnImplementionException;

/**
 * 简易连接池实现类<br>
 * DataSource应该是单实例的,一个系统只需要一个数据源;<br>
 * DataSource的主要作用是提供数据库连接,此处提供的连接实例实际上是Connection对象的一个代理对象<br>
 * 主要针对Connection的close方法做替代,不是直接关闭连接,而是回收到一个队列中,供重复使用<br>
 * 
 * @author JBOSS
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
        Connection con = DriverManager.getConnection(Config.getUrl(),
            Config.getName(), Config.getPwd());
        con = ConnectionProxy.getProxy(con, pool);// 获取代理的对象
        pool.add(con);// 添加/缓存代理对象
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
      return pool.take();//提取并移除队列中的一个对象
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
    private Connection                conn;
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
      Object proxed = Proxy.newProxyInstance(o.getClass().getClassLoader(),
          new Class[] { Connection.class }, new ConnectionProxy((Connection) o,
              pool));
      return (Connection) proxed;
    }

    /**
     * 原始对象上的方法被调用时,会调此方法
     */
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
      if (method.getName().equals("close")) {
        pool.put((Connection) proxy);//回收
        return null;//本来close就没有返回值
      } else {
        return method.invoke(conn, args);
      }
    }

  }

  @Override
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    throw new UnImplementionException();
  }

}

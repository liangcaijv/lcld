package org.lanqiao.sql.ijdbc.impl;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 整合C3P0数据连接池
 *
 */
enum C3P0DsFactory {
  me;
  private ComboPooledDataSource cpds = null;

  //工厂 调用getDataSource 获得连接池，返回给用户
  public DataSource getDataSource() {
    return cpds;
  }

  //从这到最后不用管啦，都是复制过来的。有兴趣，看看。
  private C3P0DsFactory() {
    if (cpds == null) {
      cpds = new ComboPooledDataSource();
    }
    cpds.setUser(Config.getName());
    cpds.setPassword(Config.getPwd());
    cpds.setJdbcUrl(Config.getUrl());
    try {
      cpds.setDriverClass(Config.getDriver());
    } catch (PropertyVetoException e) {
      e.printStackTrace();
    }
    cpds.setInitialPoolSize(5);
    cpds.setMaxIdleTime(20);
    cpds.setMaxPoolSize(5);
    cpds.setMinPoolSize(2);
  }

  public static void main(String[] args) throws SQLException {
    Connection con = null;
    long begin = System.currentTimeMillis();
    for (int i = 0; i < 1000000; i++) {
      con = C3P0DsFactory.me.getDataSource().getConnection();
      con.close();
    }
    long end = System.currentTimeMillis();
    System.out.println("耗时为:" + (end - begin) + "ms");
  }
}

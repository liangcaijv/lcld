package org.lanqiao.sql.ijdbc.impl;

import javax.sql.DataSource;

import org.apache.bcel.generic.RETURN;
import org.lanqiao.sql.ijdbc.exception.UnImplementionException;
/**
 * 工厂模式用于屏蔽底层实现,DataSource的实现类名应该对外界屏蔽
 * @author JBoss
 *
 */
public class DataSourceFactory {
  
  private DataSourceFactory(){}
  
  public static DataSource get(DataSourceType type ) {
    switch (type) {
    case DBCP:
     return DbcpDsFactory.me.getDataSource();
    case JNDI:
      throw new UnImplementionException("SIMPLE");
    case SIMPLE:
      return SimpleDataSource.me;
    case C3P0:
      return C3P0DsFactory.me.getDataSource();
    default:
      throw new UnImplementionException();
    }
  }
}

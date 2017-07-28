package org.lanqiao.sql.ijdbc.impl;

public enum DataSourceType {
  /**
   * 自定义用于研究的连接池，不可用于生产环境
   */
  SIMPLE, 
  DBCP, 
  C3P0,
  JNDI;
}

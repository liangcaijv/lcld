package org.lanqiao.sql.ijdbc.impl;

import java.io.IOException;
import java.util.Properties;

class Config {
  private static String driver;
  private static String url;
  private static String name;
  private static String pwd;
  private static int poolSize;
  //加载配置项
  static{
    try {
      Properties props = new Properties();//Properties是Map的子类
      //从属性文件加载键值对
      
      props.load(SimpleDataSource.class.getResourceAsStream("jdbc.properties"));
      driver = props.getProperty("driver");
      url = props.getProperty("url");
      name = props.getProperty("name");
      pwd=props.getProperty("pwd");
      poolSize=Integer.parseInt(props.getProperty("poolSize"));
      if (poolSize<=0) {
        poolSize = 5;
      }
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(),e);
    }
  }
  public static String getDriver() {
    return driver;
  }
  public static String getUrl() {
    return url;
  }
  public static int getPoolSize() {
    return poolSize;
  }
  public static String getPwd() {
    return pwd;
  }
  public static String getName() {
    return name;
  }
}

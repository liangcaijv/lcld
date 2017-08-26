package org.lanqiao.sql.ijdbc.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.Properties;

class Config {
  private static String driver;
  private static String url;
  private static String name;
  private static String pwd;
  private static int    poolSize=1;
  //加载配置项
  static {
    try {
      Properties props = new Properties();//Properties是Map的子类
      //从属性文件加载键值对

      ClassLoader classLoader = Config.class.getClassLoader();
      InputStream inStream = classLoader.getResourceAsStream("jdbc.properties");
    
      props.load(inStream);
      driver = props.getProperty("driver");
      url = props.getProperty("url");
      name = props.getProperty("name");
      pwd = props.getProperty("pwd");
      String size = props.getProperty("poolSize");
      if(size!=null){
        poolSize = Integer.parseInt(size);
        if (poolSize <= 0) {
          poolSize = 1;
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
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

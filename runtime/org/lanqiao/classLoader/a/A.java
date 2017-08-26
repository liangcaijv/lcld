package org.lanqiao.classLoader.a;

import java.io.InputStream;
import java.util.Properties;

public class A {
  public String getUrl() throws Exception {
    Properties prop = new Properties();
//    InputStream inStream = getClass().getResourceAsStream("jdbc.properties");
    
    InputStream inStream = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
    prop.load(inStream);
    return prop.getProperty("url");
  }
}

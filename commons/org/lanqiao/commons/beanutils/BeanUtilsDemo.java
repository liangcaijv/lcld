package org.lanqiao.commons.beanutils;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilsDemo {
  public static <T> T map2Bean(Map<String, String> map, Class<T> claz) throws Exception{
    T obj = claz.newInstance();
    
//    for (Entry<String, String> entry : map.entrySet()) {
//      String key = entry.getKey();
//      String value = entry.getValue();
//      BeanUtils.copyProperty(obj, key, value);
//    }
    BeanUtils.populate(obj, map);
    
    return obj;
  }
  
  
}

package org.lanqiao.commons.beanutils;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;
import org.lanqiao.reflect.Student;

public class BeanUtilsDemoTest {

  @Test
  public void testMap2Bean() throws Exception {
    Map<String, String> map = new HashMap<String, String>();
    map.put("name", "zhangsan");
    map.put("age", "10");
    map.put("other", "123");
    Student stu = BeanUtilsDemo.map2Bean(map, Student.class);
    
    System.out.println(PropertyUtils.getProperty(stu, "age"));
    
    
    System.out.println(stu);
  }

}

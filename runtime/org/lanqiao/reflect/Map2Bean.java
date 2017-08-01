package org.lanqiao.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Map2Bean {
  private static final Logger LOGGER = LoggerFactory.getLogger(Map2Bean.class);
  public static <T> T map2Bean(Map<String, String> map, Class<T> claz)
      throws Exception {
    // 根据claz实例化一个对象
    T obj = claz.newInstance();
    // 迭代map
    // 每个key找claz中的一个Method：set`Key`()
    // 反射调用该方法，传入value
    for (Entry<String, String> entry : map.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
      String methodName = "set" + upcaseFirstLetter(key);
      // 获得目标类型
      Field field;
      try {
        field = claz.getDeclaredField(key);
      } catch (Exception e) {
        LOGGER.warn("没有对应的属性："+key);
        continue;
      }
      Class fieldType = field.getType();
      Method setter;
      try {
        switch (fieldType.getName()) {
        case "int":
          setter = claz.getMethod(methodName, int.class);
          setter.invoke(obj, Integer.parseInt(value));
          break;
        case "float":
          setter = claz.getMethod(methodName, float.class);
          setter.invoke(obj, Float.valueOf(value));
          break;
        default:
          setter = claz.getMethod(methodName, String.class);
          setter.invoke(obj, value);
        }
      } catch (Exception e) {
        LOGGER.warn("没有对应的setter方法或注入值失败："+key);
        continue;
      }
    }

    return obj;

  }

  /**
   * 将key的首字母大写
   * @param key
   * @return
   */
  private static String upcaseFirstLetter(String key) {
    StringBuilder sb = new StringBuilder(("" + key.charAt(0)).toUpperCase());
    sb.append(key.substring(1));
    return sb.toString();
  }

  public static void main(String[] args) throws Exception {
    System.out.println(upcaseFirstLetter("name"));
    Map<String, String> map = new HashMap<String, String>();
    map.put("name", "zhangsan");
    map.put("age", "10");
    map.put("other", "123");
    Student stu = map2Bean(map, Student.class);
    System.out.println(stu);

  }
}

package org.lanqiao.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.lanqiao.sql.jdbc_utils_2017.JDBCFacadeImpl;
import org.lanqiao.sql.jdbc_utils_2017.JDBCFacadeImpl2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rs2Bean {
//  private static final Logger LOGGER = LoggerFactory.getLogger(Rs2Bean.class);

  public static <T> List<T> rs2Bean(ResultSet rs, Class<T> claz)
      throws Exception {
    List<T> list = new ArrayList<T>();
    String[] colNames = getColNames(rs);
    //    迭代结果集的每一行
    while (rs.next()) {
      //        每一行对应一个claz实例
      T obj = claz.newInstance();
      populateInstance(rs, claz, colNames, obj);
      list.add(obj);
    }
    return list;
  }

  private static <T> void populateInstance(ResultSet rs, Class<T> claz,
      String[] colNames, T obj) throws SQLException {
    for (String columnName : colNames) {
      String value = rs.getString(columnName);
      String methodName = "set" + upcaseFirstLetter(columnName);
      
      // 获得目标类型
      Field field;
      try {
        field = claz.getDeclaredField(columnName);
      } catch (Exception e) {
//        LOGGER.warn("没有对应的属性：" + columnName);
        System.out.println("没有对应的属性：" + columnName);
        continue;
      }
      
      Class fieldType = field.getType();
      
      try {
        invokeSetter(claz, obj, value, methodName, fieldType);
      } catch (Exception e) {
//        LOGGER.warn("没有对应的setter方法或注入值失败：" + columnName);
        System.out.println("没有对应的setter方法或注入值失败：" + columnName);
        continue;
      }
      
    }
  }

  private static <T> void invokeSetter(Class<T> claz, T obj, String value,
      String methodName, Class fieldType) throws NoSuchMethodException,
      IllegalAccessException, InvocationTargetException {
    Method setter;
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
  }

  public static String[] getColNames(ResultSet rs) throws SQLException {
    // 分析元数据，得到所有列的列名
    ResultSetMetaData metaData = rs.getMetaData();
    int colCount = metaData.getColumnCount();
    String[] colNames = new String[colCount];
    for (int i = 0; i < colCount; i++) {
      colNames[i] = metaData.getColumnName(i+1);
    }
    return colNames;
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

}

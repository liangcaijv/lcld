package org.lanqiao.sql.jdbc_utils_2017;

import java.sql.ResultSet;

/**
 * JDBC操作的简单工具，封装繁琐的连接获取过程，为用户提供便捷的SQL操作
 * 
 * @author zhengwei
 *
 */
public interface JDBCFacade {
  /**
   * 提供查询功能
   * 
   * @param sql
   *          查询语句
   * @return 结果集
   */
  ResultSet executeQuery(String sql);

  /**
   * 提供查询功能，支持参数化sql
   * 
   * @param sql
   *          查询语句，用?作为占位符
   * @param args
   *          sql的参数
   * @return 结果集
   */
  ResultSet executeQuery(String sql, Object... args);

  /**
   * 执行增删改
   * 
   * @param sql
   * @return 受影响的行数
   */
  int executeUpdate(String sql);

}

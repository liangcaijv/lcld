package org.lanqiao.sql.jdbc_utils_2017;

import java.sql.SQLException;
/**
 * 数据访问层的异常统一包装成此运行时异常
 * @author zhengwei
 *
 */
public class DataAccessException extends RuntimeException {

  public DataAccessException(SQLException e) {
    super(e);
  }

}

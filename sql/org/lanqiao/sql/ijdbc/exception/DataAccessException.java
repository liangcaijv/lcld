package org.lanqiao.sql.ijdbc.exception;

import java.sql.SQLException;

public class DataAccessException extends RuntimeException {

  public DataAccessException(SQLException e) {
    super(e);
  }

}

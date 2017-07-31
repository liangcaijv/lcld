package org.lanqiao.sql.jdbc_utils_2017;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class JDBCFacadeImpl2Test {

  @Test
  public void testExecuteQueryStringObjectArray() throws SQLException {
    String sql = "select * from user limit ?";
    ResultSet rs = JDBCFacadeImpl2.me.executeQuery(sql , 10);
    assertTrue(rs.next());
  }

}

package org.lanqiao.sql.jdbc_utils_2017;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.lanqiao.reflect.Rs2Bean;

public class JDBCFacadeImpl2Test {

  @Test
  public void testExecuteQueryStringObjectArray() throws SQLException {
    String sql = "select * from user limit ?";
    ResultSet rs = JDBCFacadeImpl2.me.executeQuery(sql , 10);
    String[] colNames = Rs2Bean.getColNames(rs);
    for (int i = 0; i < colNames.length; i++) {
      System.out.println(colNames[i]);
    }
    while (rs.next()) {
      System.out.println(rs.getString(1));
      System.out.println(rs.getString(2));
    }
    assertTrue(rs.next());
  }

}

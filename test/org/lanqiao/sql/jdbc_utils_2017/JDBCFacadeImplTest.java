package org.lanqiao.sql.jdbc_utils_2017;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class JDBCFacadeImplTest {

  @Test
  public void testExecuteQuery() throws SQLException {
    String sql = "select * from user";
    ResultSet rs = JDBCFacadeImpl.me.executeQuery(sql );
    assertTrue(rs.next());
  }
  @Test
  public void testExecuteQueryWithArgs() throws SQLException{
    String sql = "select * from user where id = ?";
    ResultSet rs = JDBCFacadeImpl.me.executeQuery(sql, 1);
    rs.next();
    assertEquals("土豆", rs.getString("nick_name"));
  }
}

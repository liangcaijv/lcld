package org.lanqiao.sql.util1;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.RowSet;

import org.junit.Test;

public class JdbcSimpleFacadeUtilTest {

  @Test
  public void testExeQuery() throws SQLException {
    RowSet rowSet = JdbcSimpleFacadeUtil.exeQuery("select * from user");
    assertTrue(rowSet.next());
  }

  @Test
  public void testUpdate() {
    fail("Not yet implemented");
  }

  @Test
  public void testExecuteBacthStringArray() {
    fail("Not yet implemented");
  }

  @Test
  public void testExecuteBacthListOfString() {
    fail("Not yet implemented");
  }

  @Test
  public void testExists() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetColumnData() {
    fail("Not yet implemented");
  }

  @Test
  public void testFreeConnection() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetConnection() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetDataSource() {
    fail("Not yet implemented");
  }

}

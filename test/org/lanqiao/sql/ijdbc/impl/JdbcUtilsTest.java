package org.lanqiao.sql.ijdbc.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.lanqiao.sql.ijdbc.JdbcOperation;

public class JdbcUtilsTest {

  @Test
  public void testGetConnection() {
   JdbcOperation jdbcOperation = JdbcOperation.of(DataSourceType.SIMPLE);
   assertNotNull(jdbcOperation.getConnection());
   User user = jdbcOperation.queryForUniqueBean("select * from user limit 1", User.class);
   assertEquals("89921218@qq.com", user.getUsername());
   System.out.println(user.getNickName());
  }

}

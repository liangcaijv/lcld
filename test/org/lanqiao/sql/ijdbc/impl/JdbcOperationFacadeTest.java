package org.lanqiao.sql.ijdbc.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.lanqiao.sql.ijdbc.JdbcOperationFacade;


public class JdbcOperationFacadeTest {

  @Test
  public void testGetConnection() {
   JdbcOperationFacade jdbcOperation = JdbcOperationFacade.of(DataSourceType.SIMPLE);
   assertNotNull(jdbcOperation.getConnection());
   
   User user = jdbcOperation
       .queryForUniqueBean("select * from user limit 1", User.class);
   
   List<User> users = jdbcOperation
       .queryForList("select * from user where age>? limit 1,10", User.class,10);
   
   assertEquals("89921218@qq.com", user.getUsername());
   System.out.println(user.getNickName());
  }

}

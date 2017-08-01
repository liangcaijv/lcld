package org.lanqiao.reflect;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.List;

import org.junit.Test;
import org.lanqiao.sql.jdbc_utils_2017.JDBCFacadeImpl2;

public class Rs2BeanTest {

  @Test
  public void testRs2Bean() throws Exception {
    String sql = "select * from user limit ?";
    ResultSet rs = JDBCFacadeImpl2.me.executeQuery(sql, 10);

    List<User> list = Rs2Bean.rs2Bean(rs, User.class);
    System.out.println(list);
  }

}

package org.lanqiao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 最初步的jdbc使用案例的改进，利用JDK7自动释放资源的特性来改写代码
 * 
 * @author zhengwei
 *
 */
public class JdbcDemoJDK7 {

  public static void main(String[] args) {
    try {
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    try (Connection conn = DriverManager.getConnection(DbConst.url,DbConst.user, DbConst.pwd);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from user");) {
      while (rs.next()) {
        System.out
            .println(rs.getString("id") + "\t" + rs.getString("username"));
        System.out.println("----------------------------------");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}

package org.lanqiao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCDemo3 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String name = scanner.nextLine();
    String pwd = scanner.nextLine();

    boolean b = login(name, pwd);
    if (b)
      System.out.println("登录成功");
    else
      System.out.println("登录失败");
  }

  private static boolean login(String name, String pwd) {
    Connection conn = null;
    try {
      // 1.register driver
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
      //      Class.forName("com.mysql.jdbc.Driver");
      // 2.url
      String url = DbConst.url;//协议+地址+端口+SID
      String username = DbConst.user;
      String pwd_db = DbConst.pwd;
      conn = DriverManager.getConnection(url, username, pwd_db);
      /*Statement stmt = conn.createStatement();
      String sql = "select * from users where name='"+name+"' and pwd='"+pwd+"'";
      System.out.println("sql==="+sql);*/
      String sql = "select * from users where name=? and pwd=?";
      //      sql = "insert into employees values(?,?,?,?)";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, name);
      pstmt.setString(2, pwd);
      ResultSet rs = pstmt.executeQuery();
      return rs.next();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (null != conn)
          conn.close();
      } catch (SQLException e) {
      }
    }
    return false;
  }

}

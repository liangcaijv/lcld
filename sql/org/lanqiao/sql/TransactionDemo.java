package org.lanqiao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDemo {
  public static void main(String[] args) {
    Connection conn = null;
    try {
      // 1.register driver
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
      //      Class.forName("com.mysql.jdbc.Driver");
      // 2.url
      String url = DbConst.url;//协议+地址+端口+SID
      String username = DbConst.user;
      String pwd = DbConst.pwd;
      conn = DriverManager.getConnection(url, username, pwd);// 3.conn
      String sql = "insert into users name(id,name) values(?,?)";
      conn.setAutoCommit(false);

      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, 6);
      pstmt.setString(2, "666");
      pstmt.executeUpdate();

      pstmt.setInt(1, 2);
      pstmt.setString(2, "bbb");
      pstmt.executeUpdate();

      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    } finally {
      try {
        if (null != conn)
          conn.close();
      } catch (SQLException e) {
        // ignore
      }
    }
  }
}

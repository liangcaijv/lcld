package org.lanqiao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 新增
 * @author zhengwei
 *
 */
public class JDBCDemo2 {
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
			Statement stmt = conn.createStatement();// 4.Statement instance
			int i = stmt.executeUpdate("insert into users (id,name) values(123,'zhangsan')");//执行sql
		} catch (SQLException e) {
			e.printStackTrace();
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

package org.lanqiao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementDemo {
	public void update(User user) {
		Connection conn = null;
		try {
			conn = getConnection();
			String sql = "update user set name=?,password=?,username=?,isadmin=? where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getIsAdmin());
			pstmt.setInt(5, user.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			closeConnection(conn);
		}

	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
//			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());// 1.加载驱动
			String url = "jdbc:oracle:thin:@localhost:1521:XE";// 2.URL
			String username = "dev";
			String pwd = "dev123";
			conn = DriverManager.getConnection(url, username, pwd);// 3.获取连接
			return conn;
		} catch (SQLException e) {
			throw e;
		}
	}

	public void closeConnection(Connection conn) {
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
}

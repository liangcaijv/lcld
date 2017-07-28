package org.lanqiao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 最初步的jdbc使用案例
 * @author zhengwei
 *
 */
public class JdbcDemo {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			// 1.register driver
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//			Class.forName("com.mysql.jdbc.Driver");
			
			// 2.url、username、password
//			String url = DbConst.url;//协议+地址+端口+SID
//			String username = DbConst.user;
//			String pwd = DbConst.pwd;
			// 3.conn
			conn = DriverManager.getConnection(DbConst.url, DbConst.user, DbConst.pwd);
			// 4.Statement
			Statement stmt = conn.createStatement();
			// 5.执行sql，如果是查询会得到一个结果集
			ResultSet rs = stmt.executeQuery("select * from user");
			//元数据
			ResultSetMetaData md =  rs.getMetaData();
			int columnCount = md.getColumnCount();
			for(int i=1;i<=columnCount;i++){
				System.out.println(md.getColumnName(i)+"\t"+md.getColumnTypeName(i));//获取列的名称及列的数据类型
			}
			System.out.println("----------------------------------");
			// 6.处理结果集result
			while (rs.next()) {
//				System.out.println(rs.getString("table_name")+"\t"+rs.getString("tablespace_name"));
				System.out.println(rs.getString("id")+"\t"+rs.getString("username"));
				System.out.println(rs.getString(1)+"\t"+rs.getString(2));
				System.out.println("----------------------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != conn)
					conn.close();	// 7.close
			} catch (SQLException e) {
				// ignore
			}
		}
	}

}

package org.lanqiao.sql.util1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

public class DbUtil {
	private static DbPool pool = DbcpPool.me;

  public static RowSet exeQuery(String sql) {
		Connection conn = null;
		CachedRowSet crs = null;
		try {
			conn = pool.createConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			crs = new com.sun.rowset.CachedRowSetImpl();
			crs.populate(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			freeConnection(conn);
		}
		return crs;
	}

	public static boolean update(String sql) {
		boolean bl = false;
		Connection conn = null;
		try {
			conn = pool.createConnection();
			Statement stmt = conn.createStatement();
			if (stmt.executeUpdate(sql) > 0)
				bl = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			freeConnection(conn);
		}
		return bl;
	}

	public static boolean executeBacth(String[] sqls) {
		boolean b = true;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = pool.createConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			for (int i = 0; i < sqls.length; i++) {
				stmt.addBatch(sqls[i]);
			}
			stmt.executeBatch();

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
			b = false;
			throw new RuntimeException(e);
		} finally {
			freeConnection(conn);
		}
		return b;
	}

	public static boolean executeBacth(List<String> sqls) {
		boolean b = true;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = pool.createConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			for (int i = 0; i < sqls.size(); i++) {
				stmt.addBatch((String) sqls.get(i));
			}
			stmt.executeBatch();

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
			b = false;
		} finally {
			freeConnection(conn);
		}
		return b;
	}

	public static boolean exists(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = pool.createConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			boolean bool = rs.next();
			return bool;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			freeConnection(conn);
		}
	}

	public static ColumnData getColumnData(String table) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = pool.createConnection();
			stmt = conn.createStatement();
			ResultSetMetaData rsm = stmt.executeQuery(
					"select * from (" + table + ")  where rownum<2")
					.getMetaData();
			int count = rsm.getColumnCount();
			String[] columnsName = new String[count];
			String[] columnsType = new String[count];
			for (int i = 1; i <= count; i++) {
				columnsName[(i - 1)] = rsm.getColumnName(i);
				columnsType[(i - 1)] = rsm.getColumnTypeName(i);
			}
			ColumnData localColumnData = new ColumnData(columnsName,
					columnsType);
			return localColumnData;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			freeConnection(conn);
		}
	}

	public static void freeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static Connection getConnection() throws SQLException {
		return pool.createConnection();
	}
	public static DataSource getDataSource(){
		return pool.getDataSource();
	}
}

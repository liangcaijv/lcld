package org.lanqiao.sql.jdbc_utils_2017;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum JDBCFacadeImpl implements JDBCFacade {
  me;

  static final Logger LOGGER = LoggerFactory.getLogger(JDBCFacadeImpl.class);
  final BlockingQueue<Connection> connQueue = new LinkedBlockingDeque<Connection>(
      10);

  private JDBCFacadeImpl() {
    init();
  }

  @Override
  public ResultSet executeQuery(String sql) {
    Connection conn = null;
    try {
      conn = getConn();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      return cacheRs(rs);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage(), e);
      throw new DataAccessException(e);
    } finally {
      freeConnection(conn);
    }
  }

  @Override
  public ResultSet executeQuery(String sql, Object... args) {
    Connection conn = null;
    try {
      conn = getConn();
      PreparedStatement pstmt = getPstatement(conn, sql, args);
      ResultSet rs = pstmt.executeQuery();
      return cacheRs(rs);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage(), e);
      throw new DataAccessException(e);
    } finally {
      freeConnection(conn);
    }
  }

  @Override
  public int executeUpdate(String sql) {
    Connection conn = null;
    try {
      conn = getConn();
      Statement stmt = conn.createStatement();
      return stmt.executeUpdate(sql);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage(), e);
      throw new DataAccessException(e);
    } finally {
      freeConnection(conn);
    }
  }

  private void freeConnection(Connection conn) {
    try {
      if (null != conn && !conn.isClosed()) {
        connQueue.add(conn);
      }
    } catch (SQLException e) {
      // ignore
    }
  }

  private ResultSet cacheRs(ResultSet rs) throws SQLException {
    com.sun.rowset.CachedRowSetImpl rowset = new com.sun.rowset.CachedRowSetImpl();
    rowset.populate(rs);
    return rowset;
  }

  private void init() {
    for (int i = 0; i < 10; i++) {
      try {
        connQueue.add(createConn());
      } catch (SQLException e) {
        throw new DataAccessException(e);
      }
    }
  }

  private Connection createConn() throws SQLException {
    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    Connection conn = DriverManager.getConnection(DbConst.url, DbConst.user,
        DbConst.pwd);
    return conn;
  }

  private Connection getConn() throws SQLException {
    return connQueue.poll();
  }

  private PreparedStatement getPstatement(Connection conn, String sql,
      Object[] args) throws SQLException {
    PreparedStatement pstmt = conn.prepareStatement(sql);
    for (int i = 0; i < args.length; i++) {
      pstmt.setObject(i + 1, args[i]);
    }
    return pstmt;
  }
}

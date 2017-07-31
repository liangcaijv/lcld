package org.lanqiao.sql.jdbc_utils_2017;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import javax.sql.DataSource;

import org.lanqiao.sql.ijdbc.impl.DataSourceFactory;
import org.lanqiao.sql.ijdbc.impl.DataSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum JDBCFacadeImpl2 implements JDBCFacade {
  me;

  static final Logger LOGGER = LoggerFactory.getLogger(JDBCFacadeImpl2.class);
  private final DataSource ds = DataSourceFactory.get(DataSourceType.SIMPLE);

  private JDBCFacadeImpl2() {
  }

  @Override
  public ResultSet executeQuery(String sql) {
    try(Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);) {
      return cacheRs(rs);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage(), e);
      throw new DataAccessException(e);
    } 
  }

  @Override
  public ResultSet executeQuery(String sql, Object... args) {
    try (Connection conn = ds.getConnection();
        PreparedStatement pstmt = getPstatement(conn, sql, args);
        ResultSet rs = pstmt.executeQuery(); 
        ){
      return cacheRs(rs);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage(), e);
      throw new DataAccessException(e);
    } 
  }

  @Override
  public int executeUpdate(String sql) {
    try(Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();) {
      return stmt.executeUpdate(sql);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage(), e);
      throw new DataAccessException(e);
    } 
  }


  private ResultSet cacheRs(ResultSet rs) throws SQLException {
    com.sun.rowset.CachedRowSetImpl rowset = new com.sun.rowset.CachedRowSetImpl();
    rowset.populate(rs);
    return rowset;
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

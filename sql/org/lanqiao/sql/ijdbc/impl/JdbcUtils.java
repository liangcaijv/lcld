package org.lanqiao.sql.ijdbc.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.MyResultSetIterator;
import org.apache.commons.beanutils.ResultSetDynaClass;
import org.lanqiao.commons.custom.SqlUtil;
import org.lanqiao.commons.custom.WordDealUtil;
import org.lanqiao.sql.ijdbc.JdbcOperation;
import org.lanqiao.sql.ijdbc.RowMapper;
import org.lanqiao.sql.ijdbc.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 简易JDBC操作的实现类
 * 
 * @author shadow
 * 
 */
public class JdbcUtils implements JdbcOperation {
  private Logger           logger = LoggerFactory.getLogger(JdbcUtils.class);
  private DataSource       dataSource;
  private static JdbcUtils jdbcOperation;

  public static JdbcUtils of(DataSourceType dataSourceType) {
    if (jdbcOperation == null) {
      jdbcOperation = new JdbcUtils();
    }
    jdbcOperation.dataSource = DataSourceFactory.get(dataSourceType);
    return jdbcOperation;
  }

  private PreparedStatement createPreparedStatement(Connection conn,
      String sql, Object... params) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(sql);
    for (int i = 0; i < params.length; i++) {
      if (params[i] instanceof Date) {
        Date date = (Date) params[i];
        stmt.setDate(i + 1, new java.sql.Date(date.getTime()));
      } else {
        stmt.setObject(i + 1, params[i]);
      }
    }
    if (logger.isDebugEnabled()) {
      logger.debug(SqlUtil.preSql2Sql(sql, params));
    }
    return stmt;
  }

  @Override
  public int execute(String sql, Object... params) {
    try {
      Connection conn = getConnection();
      PreparedStatement stmt = null;
      int result = -1;
      try {
        stmt = createPreparedStatement(conn, sql, params);
        result = stmt.executeUpdate();
      } finally {
        free(stmt);
        free(conn);
      }
      return result;
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  @Override
  public int[] executeBatch(String[] sqls) {
    Connection conn = null;
    Statement stmt = null;
    int[] result = null;
    try {
      conn = getConnection(false);// 修改自动提交为false
      stmt = conn.createStatement();
      for (int i = 0; i < sqls.length; i++) {
        stmt.addBatch(sqls[i]);
      }
      result = stmt.executeBatch();
      conn.commit();
    } catch (SQLException e) {
      try {
        conn.rollback();
        throw e;
      } catch (SQLException e1) {
        throw new DataAccessException(e1);
      }
    } finally {
      free(stmt);
      free(conn);
    }
    return result;
  }

  @Override
  public int executeBatch(String[] sqls, Object[][] params) {
    Connection conn = null;
    PreparedStatement stmt = null;
    int result = -1;
    try {
      conn = getConnection(false);
      for (int i = 0; i < sqls.length; i++) {
        stmt = createPreparedStatement(conn, sqls[i], params[i]);
        result += stmt.executeUpdate();
      }
      conn.commit();
    } catch (SQLException e) {
      try {
        conn.rollback();
        throw e;
      } catch (SQLException e1) {
        throw new DataAccessException(e1);
      }
    } finally {
      free(stmt);
      free(conn);
    }
    return result;
  }

  @Override
  public void free(Connection x) {
    if (x != null)
      try {
        x.close();
      } catch (SQLException e) {
        // e.printStackTrace();
      }
  }

  @Override
  public void free(PreparedStatement x) {
    if (x != null)
      try {
        x.close();
      } catch (SQLException e) {
        // e.printStackTrace();
      }
  }

  @Override
  public void free(ResultSet x) {
    if (x != null)
      try {
        x.close();
      } catch (SQLException e) {
        // e.printStackTrace();
      }
  }

  @Override
  public void free(Statement x) {
    if (x != null)
      try {
        x.close();
      } catch (SQLException e) {
        // e.printStackTrace();
      }
  }

  public Connection getConnection() {
    return getConnection(true);
  }

  public Connection getConnection(boolean autoCommit) {
    try {
      Connection conn = dataSource.getConnection();
      if (!autoCommit)
        conn.setAutoCommit(autoCommit);
      return conn;
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  @Override
  public double queryForDouble(String sql, Object... params) {
    ResultSet rs = null;
    try {
      rs = queryForResultSet(sql, params);
      if (rs.next()) {
        return rs.getDouble(1);
      } else {
        throw new SQLException("无结果");
      }
    } catch (SQLException e) {
      throw new DataAccessException(e);
    } finally {
      free(rs);
    }
  }

  @Override
  public float queryForFloat(String sql, Object... params) {
    ResultSet rs = null;
    try {
      rs = queryForResultSet(sql, params);
      if (rs.next()) {
        return rs.getFloat(1);
      } else {
        throw new SQLException("无结果");
      }
    } catch (SQLException e) {
      throw new DataAccessException(e);
    } finally {
      free(rs);
    }
  }

  @Override
  public int queryForInt(String sql, Object... params) {
    ResultSet rs = null;
    try {
      rs = queryForResultSet(sql, params);
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("无结果");
      }
    } catch (SQLException e) {
      throw new DataAccessException(e);
    } finally {
      free(rs);
    }
  }

  @Override
  public <T> List<T> queryForList(String sql, RowMapper<T> mapper,
      Object... params) {
    ResultSet rs = null;
    List<T> list = null;
    try {
      rs = queryForResultSet(sql, params);
      list = new ArrayList<T>();
      while (rs.next()) {
        list.add(mapper.mapRow(rs));
      }
    } catch (SQLException e) {
      throw new DataAccessException(e);
    } finally {
      free(rs);
    }
    return list;
  }

  @Override
  public <T> List<T> queryForList(String sql, Class<T> entityClass,
      Object... params) {
    ResultSet rs = null;
    List<T> list = new ArrayList<T>();
    try {
      // 得到结果集
      rs = queryForResultSet(sql, params);
      // -----------将结果集转为List begin---------------
      ResultSetDynaClass rsdc = new ResultSetDynaClass(rs);
      MyResultSetIterator rows = new MyResultSetIterator(rsdc);
      // 迭代结果集
      while (rows.hasNext()) {
        // 每一行转成一个对象
        T newInstance = nextRow2BeanInstance(entityClass, rsdc, rows);
        list.add(newInstance);
      }
      // -----------将结果集转为List end---------------
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new DataAccessException(e);
    } finally {
      free(rs);
    }
    return list;
  }

  @Override
  public long queryForLong(String sql, Object... params) {
    ResultSet rs = null;
    try {
      rs = queryForResultSet(sql, params);
      if (rs.next()) {
        return rs.getLong(1);
      } else {
        throw new SQLException("无结果");
      }
    } catch (SQLException e) {
      throw new DataAccessException(e);
    } finally {
      free(rs);
    }
  }

  @Override
  public Map<Object, Object> queryForMap(String sql, Object... params) {
    ResultSet rs = null;
    try {
      rs = queryForResultSet(sql, params);
      Map<Object, Object> map = new HashMap<Object, Object>();
      while (rs.next()) {
        map.put(rs.getObject(1), rs.getObject(2));
      }
      return map;
    } catch (SQLException e) {
      throw new DataAccessException(e);
    } finally {
      free(rs);
    }
  }

  @Override
  public ResultSet queryForResultSet(String sql, Object... params) {
    Connection conn = getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = createPreparedStatement(conn, sql, params);
      rs = stmt.executeQuery();
      // 离线版本的结果集——ResultSet所关联的Connection一旦关闭，那么ResultSet也就失效了
      CachedRowSet crs = new com.sun.rowset.CachedRowSetImpl();
      crs.populate(rs);
      return crs;
    } catch (SQLException e) {
      throw new DataAccessException(e);
    } finally {
      free(rs);
      free(stmt);
      free(conn);
    }
  }

  @Override
  public String queryForString(String sql, Object... params) {
    ResultSet rs = null;
    try {
      rs = queryForResultSet(sql, params);
      if (rs.next()) {
        return rs.getString(1);
      } else {
        throw new SQLException("无结果");
      }
    } catch (SQLException e) {
      throw new DataAccessException(e);
    } finally {
      free(rs);
    }
  }

  @Override
  public <T> T queryForUniqueBean(String sql, RowMapper<T> mapper,
      Object... params) {
    ResultSet rs = null;
    try {
      rs = queryForResultSet(sql, params);
      if (rs.next()) {
        return mapper.mapRow(rs);
      } else {
        throw new SQLException("无结果");
      }
    } catch (SQLException e) {
      throw new DataAccessException(e);
    } finally {
      free(rs);
    }
  }

  @Override
  public <T> T queryForUniqueBean(String sql, Class<T> entityClass,
      Object... params) {
    ResultSet rs = null;
    try {
      rs = queryForResultSet(sql, params);
      ResultSetDynaClass rsdc = new ResultSetDynaClass(rs);
      MyResultSetIterator rows = new MyResultSetIterator(rsdc);
      if (rows.hasNext()) {
        T newInstance = nextRow2BeanInstance(entityClass, rsdc, rows);
        return newInstance;
      } else {
        return null;
      }
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new DataAccessException(e);
    } finally {
      free(rs);
    }
  }

  private <T> T nextRow2BeanInstance(Class<T> entityClass,
      ResultSetDynaClass rsdc, MyResultSetIterator rows)
      throws InstantiationException, IllegalAccessException,
      InvocationTargetException {
    DynaBean row = (DynaBean) rows.next();
    T newInstance = entityClass.newInstance();
    DynaProperty[] origDescriptors = rsdc.getDynaProperties();
    for (int i = 0; i < origDescriptors.length; i++) {
      String columnName = origDescriptors[i].getName();// 列名
      String javaName = WordDealUtil.dBColumn2Java(columnName);// java变量名
      Object value = row.get(columnName);
      if (value != null) {
        BeanUtils.copyProperty(newInstance, javaName, value);
      }
    }
    return newInstance;
  }

}

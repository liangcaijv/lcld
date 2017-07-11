package org.lanqiao.sql.ijdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.lanqiao.sql.ijdbc.impl.DataSourceType;
import org.lanqiao.sql.ijdbc.impl.JdbcUtils;


/**
 * JDBC操作接口  应该是ijdbc包向外界提供的唯一接口
 * 
 * @author JBoss
 * @version 2014.6.17
 */
public interface JdbcOperation {
    static JdbcOperation of(DataSourceType dataSourceType){
      return JdbcUtils.of(dataSourceType);
    }
    /**
     * 增删改功能
     * 
     * @param sql
     *            sql语句
     * @param params
     *            sql中的参数
     * @return 变更记录数
     */
    int execute(String sql, Object... params);

    /**
     * 批处理增删改
     * 
     * @param sql
     *            多个带参sql
     * @param params
     *            参数
     * @return 变更记录数
     */
    int executeBatch(String[] sql, Object[][] params);

    /**
     * 批处理增删改
     * 
     * @param sql
     *            多个不带参的sql
     * @param params
     *            参数
     * @return 变更记录数
     */
    int[] executeBatch(String[] sql) ;

    /**
     * select功能
     * 
     * @param sql
     *            sql语句
     * @param params
     *            参数
     * @return 原生ResultSet数据集合
     */
    ResultSet queryForResultSet(String sql, Object... params);

    /**
     * select功能,提供对象映射规则,将结果集转换为对象列表
     * 
     * @param sql
     *            sql语句
     * @param mapper
     *            提供映射规则的对象
     * @param params
     *            sql参数
     * @return List<?>数据集合
     */
    @Deprecated
    <T> List<T> queryForList(String sql, RowMapper<T> mapper,
            Object... params) ;

    /**
     * select功能,针对只返回一行或者只需返回一行的数据进行封装
     * 
     * @param sql
     *            sql语句
     * @param mapper
     *            提供映射规则的对象
     * @param params
     *            sql参数
     * @return 泛型所执行的类型的对象
     */
    @Deprecated
    <T> T queryForUniqueBean(String sql, RowMapper<T> mapper,
            Object... params) ;

    /**
	 * 20140724新增的方法,用于使用反射将列中的同名字段自动赋予bean的属性,减少mapper的编码<br>
	 * 此方法要求字段名必须和属性名相同,或者字段名为下划线分隔,对应的属性名是骆驼法则
	 * @param query
	 * @param entityClass
	 * @param params
	 * @return
	 */
	<T>T queryForUniqueBean(String sql, Class<T> entityClass, Object... params);

	/**
     * select功能,取查询结果的前两列,作为map中元素的键和值
     * 
     * @param sql
     *            sql语句
     * @param params
     *            sql参数
     * @return 由查询的第一列和第二列组成键值对的map
     */
    Map<Object, Object> queryForMap(String sql,
            Object... params) ;

    /**
     * select功能,针对返回的单行,取第一列(一般调用者只查询一列),转换为int
     * 
     * @param sql
     * @return 所查询的整数值
     */
    int queryForInt(String sql, Object... params);

    /**
     * select功能,针对返回的单行,取第一列(一般调用者只查询一列),转换为Float
     * 
     * @param sql
     * @return 所查询的Float
     */
    float queryForFloat(String sql, Object... params);

    /**
     * select功能,针对返回的单行,取第一列(一般调用者只查询一列),转换为Double
     * 
     * @param sql
     * @return 所查询的Double
     */
    double queryForDouble(String sql, Object... params);

    /**
     * select功能,针对返回的单行,取第一列(一般调用者只查询一列),转换为long
     * 
     * @param sql
     * @return 所查询的长整型数值
     */
    long queryForLong(String sql, Object... params);

    /**
     * select功能,针对返回的单行,取第一列(一般调用者只查询一列),转换为String
     * 
     * @param sql
     * @return 所查询的字符串
     */
    String queryForString(String sql, Object... params);

    /**
     * 释放Connection资源
     * 
     * @param x
     */
    void free(Connection x);

    /**
     * 释放Statement资源
     * 
     * @param x
     */
    void free(Statement x);

    /**
     * 释放PreparedStatement资源
     * 
     * @param x
     */
    void free(PreparedStatement x);

    /**
     * 释放ResultSet资源
     * 
     * @param x
     */
    void free(ResultSet x);

    /**
     * 获取数据库链接
     * 
     * @return Connection对象
     */
    Connection getConnection() ;

    /**
     * 获取数据库链接
     * 
     * @param autoCommit
     *            是否自动提交
     * @return Connection对象
     */
    Connection getConnection(boolean autoCommit) ;

    /**
     * 20140716新增的方法,用于使用反射将列中的同名字段自动赋予bean的属性,减少mapper的编码<br>
     * 此方法要求字段名必须和属性名相同,或者字段名为下划线分隔,对应的属性名是骆驼法则<br>
     * 如user_name列可自动填充到userName属性
     * @param query sql
     * @param entityClass  实体类的Class对象
     * @param params sql参数
     * @return
     */
	<T>List<T> queryForList(String query, Class<T> entityClass, Object... params) ;

}

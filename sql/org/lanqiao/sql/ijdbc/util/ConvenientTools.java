package org.lanqiao.sql.ijdbc.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lanqiao.commons.custom.StringUtils;
import org.lanqiao.sql.ijdbc.JdbcOperation;
import org.lanqiao.sql.ijdbc.impl.DataSourceType;

/**
 * 一些便利的方法,多数与数据库有关
 * 
 * @author 郑未
 * 
 */
public class ConvenientTools {
	private static JdbcOperation jdbcUtils = JdbcOperation.of(DataSourceType.DBCP);

	/**
	 * 根据一个只查询两个字段的sql的查询结构构造一段html代码片段<br>
	 * 返回下拉列表（select标签）的innerHTML，如<br>
	 * <option value='A'>A</option> <option value='B'>B</option>
	 * 
	 * @param sql
	 *            只能查两个字段，第一个字段代表下拉列表中的值，第二个代表显示的内容
	 * @param focus
	 *            焦点 即当前值
	 * @param params
	 *            sql的参数
	 * @return 带焦点的下拉列表
	 * @throws SQLException
	 */
	public static String getOption(String sql, String focus, Object... params)
			throws SQLException {
		String option = "";
		Map<String, String> map = getMap(sql, params);
		for (String k : map.keySet()) {
			String v = map.get(k);
			String selected = "";
			if (StringUtils.hasLength(focus) && focus.trim().equals(k.trim()))
				selected = "selected";
			option += "<option value='" + k + "'" + selected + ">" + v
					+ "</option>";
		}
		return option;

	}

	/**
	 * 将一条只查询2个字段的sql的查询结果转换为Map
	 * 
	 * @param sql
	 *            只能查两个字段，第一个字段key，第二个代表value
	 * @param params
	 *            sql的参数
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, String> getMap(String sql, Object... params)
			throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		ResultSet rs = jdbcUtils.queryForResultSet(sql, params);
		while (rs != null && rs.next()) {
			String k = rs.getString(1);// 值
			String v = rs.getString(2);// 名称
			map.put(k, v);
		}
		return map;

	}
	/**
	 * 根据一个只查询1个字段的sql的查询结构构造一个List<String>对象<br>
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static List<String> getLists(String sql) throws SQLException {
		String option = "";
		ResultSet rs = jdbcUtils.queryForResultSet(sql);
		List<String> list = new ArrayList<String>();
		while (rs != null && rs.next()) {
			String value = rs.getString(1);// 值
			list.add(value);
		}
		return list;
	}

	/**
	 * 计算某字段的最大值的下一个值
	 * @param tableName 表名
	 * @param columnName 字段名
	 * @param condition 查询条件
	 * @return
	 * @throws SQLException
	 */
	public static int getSeq(String tableName, String columnName, String condition)
			throws SQLException {
		int seq = 0;
		String sql = "";
		if (!condition.equals(""))
			sql = "select max(" + columnName + ")  from " + tableName + " where 1=1 and "
					+ condition;
		else
			sql = "select max(" + columnName + ")  from " + tableName;
//		System.out.println("sql--------------" + sql);
		ResultSet rs = jdbcUtils.queryForResultSet(sql);
		while (rs.next()) {
//			System.out.println("max------------" + rs.getInt(1));
			seq = rs.getInt(1) + 1;
		}
		return seq;
	}
	/**
	 * 计算表的id字段的最大值的下一个值，列名不为id的不可用这个方法
	 * @param tableName 表名
	 * @return
	 * @throws SQLException
	 */
	public static int getSeq(String tableName) throws SQLException {
		int seq = 1;
		String sql = "select max(id)  from " + tableName;
		ResultSet rs = jdbcUtils.queryForResultSet(sql);
		while (rs.next()) {
			seq += rs.getInt(1) + 1;
		}
		return seq;
	}

	/***
	 * 通过条件取单值
	 * 
	 * @param col
	 *            String 需要得到数据的列 例如：pwd
	 * @param table
	 *            String 表名 例如：user（表）
	 * @param condition
	 *            String 条件 例如：name='zhengwei'
	 * 
	 *            效果相当于：select pwd from user where name='zhengwei'
	 * @throws SQLException
	 * **/
	public static String getValueByCond(String col, String table,
			String condition, Object... params) throws SQLException {
		String sql = "select " + col + " from " + table + " where " + condition;
		// System.out.println("sql in Db_getValue......."+sql);
		ResultSet rs = jdbcUtils.queryForResultSet(sql, params);
		String result = "";
		if (rs != null && rs.next()) {
			result = rs.getString(1);
		} else {
			result = "";
		}
		return result;
	}

	/**
	 * 根据条件查询得到单行的多个字段，字段值组成数组--》返回
	 * 
	 * @param col
	 * @param table
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public static String[] getValueByCond(String[] col, String table,
			String condition) throws SQLException {
		String cols = "";
		for (String s : col) {
			cols += s + ",";
		}
		cols = cols.substring(0, cols.lastIndexOf(","));
		String sql = "select " + cols + " from " + table + " where "
				+ condition;
		System.out.println("sql in Db_getValue......." + sql);
		ResultSet rs = jdbcUtils.queryForResultSet(sql);
		String[] result = new String[col.length];
		if (rs != null && rs.next()) {
			for (int i = 0; i < col.length; i++) {
				result[i] = rs.getString(i + 1);
			}
		}
		return result;
	}

	public static void main(String[] args) throws SQLException {
		System.out
				.println(getMap("select id,username from cmn_rights_user").size());
	}
}
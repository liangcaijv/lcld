package org.lanqiao.commons.custom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对名称、地址等字符串格式的内容进行格式检查 或者格式化的工具类
 * 
 * @author Ai92
 */
public class WordDealUtil {

	/**
	 * 将 Java 对象名称（每个单词的头字母大写）按照 数据库命名的习惯进行格式化 格式化后的数据为小写字母，并且使用下划线分割命名单词
	 * 
	 * 例如：employeeInfo 经过格式化之后变为 employee_info
	 * 
	 * @param name
	 *            Java 对象名称
	 */
	public static String wordFormat4DB(String name) {
		if (name==null) {
			return null;
		}
		if (name.length()==0) {
			return "";
		}
		Pattern p = Pattern.compile("[A-Z]");
		Matcher m = p.matcher(name);
		StringBuffer sb = new StringBuffer();

		while (m.find()) {
			if(m.start() != 0) 
				m.appendReplacement(sb, "_" + m.group());
		}
		return m.appendTail(sb).toString().toLowerCase();
	}
	/**
	 * 将数据库命名习惯的列名转换为 Java变量名(下划线替换为骆驼法则)<br>
	 * 
	 * 例如：employee_info 经过格式化之后变为employeeInfo 
	 * 
	 * @param name  字段名/列名
	 */
	public static String dBColumn2Java(String name) {
		if (name==null) {
			return null;
		}
		if (name.length()==0) {
			return "";
		}
		String columnName = name.toLowerCase();
		Pattern p = Pattern.compile("_([a-z])");
		Matcher m = p.matcher(columnName);
		StringBuffer sb = new StringBuffer();

		while (m.find()) {
			if(m.start() != 0) 
				m.appendReplacement(sb, m.group(1).toUpperCase());
		}
		String string = m.appendTail(sb).toString();
//		String string = sb.toString();
		return string;
	}
}

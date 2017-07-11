package org.lanqiao.commons.custom;



public class SqlUtil {

	public static String preSql2Sql(String sql, Object... params) {
		StringBuilder sb = new StringBuilder(sql);
		int start = 0;
		for (int i = 0; i < params.length; i++) {
			start = sb.indexOf("?", start);
			if (params[i] instanceof String) {
				sb.replace(start, start+1, "'"+params[i]+"'");
			}else {
				sb.replace(start, start+1, params[i]+"");
			}
		}
		return sb.toString();
	}

}

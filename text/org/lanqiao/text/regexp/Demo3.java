package org.lanqiao.text.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo3 {
	//从日志文件中提取ip及登录时间
	public static void main(String[] args) {
		String src = "192.168.0.100 - - [26/Feb/2001:10:56:03 -0500]\nGet /\"login.jsp HTTP/1.1\" 200 15\n"
				+ "192.168.0.101 - - [26/Feb/2001:10:56:05 -0550]\nGet /\"login.jsp HTTP/1.1\" 200 15\n";
//		System.out.println(src);
		String regex = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\s-\\s-\\s\\[([^\\]]+)\\]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(src);
		String regex2 = "\\d{1,2}/[a-zA-Z]+/\\d{4}:(\\d{1,2}):\\d{1,2}:\\d{1,2}\\s-[0-9]{4}"; 
		Pattern p2 = Pattern.compile(regex2);
		while(m.find()){
			System.out.println(m.group(1));
			String date = m.group(2);
			Matcher m2 = p2.matcher(date);
			if(m2.matches()){
				System.out.println("小时:"+m2.group(1));
			}
		}
	}
}

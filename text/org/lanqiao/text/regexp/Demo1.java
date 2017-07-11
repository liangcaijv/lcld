package org.lanqiao.text.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo1 {
	//June 26, 1951 找出月份
	public static void main(String[] args) {
//		d1();
		d2();
	}

	private static void d2() {
		String src = "asdjakdjak June 26, 1951 dajdsajkdja July 26, 1951 sdfsffs";
		String regex = "([a-z|A-Z]+)\\s+[0-9]{1,2},\\s*[0-9]{4}";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(src);

		while(m.find()){//查找
			String month = m.group(1);//找正则表达式中第一个()包含的内容
			System.out.println(month);
		}
	}

	private static void d1() {
		String src = "June 26, 1951";
		String regex = "([a-z|A-Z]+)\\s+[0-9]{1,2},\\s*[0-9]{4}";
//		System.out.println(regex);
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(src);
//		System.out.println("是否完全匹配"+m.matches());
		if(m.matches()){
			String month = m.group(1);//找正则表达式中第一个()包含的内容
			System.out.println(month);
		}
	}
	
	
}

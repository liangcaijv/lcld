package org.lanqiao.text.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo2 {
//	搜索999-99-9999这种格式的字符串
	public static void main(String[] args) {
		String src = "dahuwh821-21-9999duieajkd672-34-8722cde765-22-654aa";
		src = "dada8765abdajda8273daus8976ab";
		String regex = "[0-9]{3}\\-[0-9]{2}\\-[0-9]{4}";
		regex = "[0-9]{4}[a-zA-Z]{2}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(src);
		while(m.find()){
			System.out.println(m.group());
		}
	}
}

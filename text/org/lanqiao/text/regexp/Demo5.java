package org.lanqiao.text.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo5 {
	public static void main(String[] args) {
		String src="<a href='abc.html'>dajs</a>\n<a href='abc.html'>abc</a>";
		String regex = "<a\\s+href=\'([^']+)\'";
	/*	System.out.println(src);
		System.out.println("regex="+regex);
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(src);
		while(m.find()){
			System.out.println(m.group(1));
			
		}*/
		String n_src = src.replaceAll(regex, "<a href='def.html'");
		System.out.println(n_src);
	}
}

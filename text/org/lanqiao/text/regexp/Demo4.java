package org.lanqiao.text.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo4 {
	public static void main(String[] args) {
		String regex = "\\s+([a-zA-Z]+)=\"(.*?)\"";
		String src = "<font face=\"Aria,Serif\" size=\"+2\" color=\"red\">";
		System.out.println("src="+src);
		System.out.println("rege="+regex);
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(src);
		while(m.find()){
			System.out.print(m.group(1)+"=");
			System.out.println(m.group(2));
		}
	}
}

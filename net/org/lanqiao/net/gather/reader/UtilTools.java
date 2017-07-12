package org.lanqiao.net.gather.reader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ϊ���ɼ�������ש���ߣ��ṩ�ͻ��˵�����ƽ̨ 
 * @���� Gabriel 
 * @���� http://blog.csdn.net/zdsdiablo
 */
public class UtilTools {
	private static String[] timeFormats={
			"\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d",//yyyy-MM-dd hh-mm-ss
			"\\d\\d\\d\\d-\\d\\d-\\d\\d",
			"\\d\\d\\d\\d��\\d\\d��\\d\\d��\\d\\d:\\d\\d",
			"\\d\\d\\d\\d��\\d\\d��\\d\\d��"};
	
	public static boolean isEmpty(String val){
		if(val==null || val.trim().equals(""))
			return true;
		return false;
	}
	
	public static boolean isNotEmpty(String val){
		return !isEmpty(val);
	}
	
	
	public static String getTimeLine(String str){
		if(isEmpty(str)) return "";
		
		String result="";
		
		for(String format : timeFormats){
			Pattern p = Pattern.compile(format);
			Matcher m = p.matcher(str);
			if(m.find()){
				result=m.group();
				break;
			}
		}
		return result;
	}
}

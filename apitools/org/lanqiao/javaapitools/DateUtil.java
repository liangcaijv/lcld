package org.lanqiao.javaapitools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String format1(Date date){
		return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(date);
	} 
}

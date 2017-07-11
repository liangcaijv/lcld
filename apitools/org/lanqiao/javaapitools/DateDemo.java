package org.lanqiao.javaapitools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {
	public static void main(String[] args) {
//		long t1 = System.currentTimeMillis();
//		f();
//		System.out.println("执行了："+(System.currentTimeMillis()-t1));
		
		Date date = new Date();//==new Date(System.currentTimeMillis());
//		date = new Date(2012,1,1);//该方法已经过时
		System.out.println(date);
		
		String s1 = DateFormat.getInstance().format(date);//SHORT风格
		s1 = DateFormat.getDateInstance(DateFormat.FULL).format(date);//完整风格
		//风格的选择有FULL LONG MEDIUM  SHORT
		
		try {//将字符串解析为Date对象
			Date date2 =	DateFormat.getDateInstance(DateFormat.LONG).parse("2012年8月10日");
			System.out.println("222222222==="+date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		String s2 = new SimpleDateFormat("yy年MM月dd日").format(new Date());
		System.out.println("自定义时间格式："+s2);
		
	}
	static void f(){
		
	}
}

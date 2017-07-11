package org.lanqiao.javaapitools;

import java.util.Calendar;
import java.util.Locale;

public class CalendarDemo {
	public static void main(String[] args) {
		/*Calendar calendar = Calendar.getInstance();
		System.out.println("月份："+(calendar.get(Calendar.MONTH)+1));
		System.out.println("日期："+calendar.get(Calendar.DAY_OF_MONTH));
		
		calendar.set(Calendar.YEAR, 1990);
		System.out.println(calendar.getTime());*/
		System.out.println("日\t一\t二\t三\t四\t五\t六");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 5);
		
		int currentMonth = calendar.get(Calendar.MONTH);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);//代表本月第一天
		int weekOfFirstDay = calendar.get(Calendar.DAY_OF_WEEK);//第一天是星期几
		//循环输出第一天前面的空白
		for(int i=1;i<weekOfFirstDay;i++){
			System.out.print("\t");
		}
		//开始打印日期，直到超出本月日期
		while(calendar.get(Calendar.MONTH)==currentMonth){
			System.out.print(calendar.get(Calendar.DAY_OF_MONTH)+"\t");
			int week = calendar.get(Calendar.DAY_OF_WEEK);//星期几
			if(week==7)//周六换行
				System.out.println();
			calendar.add(Calendar.DAY_OF_MONTH, 1);//日期增1
		}
	}
}
/*
日 一 二 三 四 五 六

*/
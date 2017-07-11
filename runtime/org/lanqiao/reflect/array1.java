package org.lanqiao.reflect;

import java.lang.reflect.*;

public class array1 {
	public static void main(String args[]) {
		try {
			Class cls = Class.forName("java.lang.String");
			Object arr = Array.newInstance(cls, 10);//创建长度为10的字符串数组
			Array.set(arr, 5, "this is a test");//为下标为5的元素赋值
			String s = (String) Array.get(arr, 5);//获取下标为5的元素的值
			System.out.println(s);
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}

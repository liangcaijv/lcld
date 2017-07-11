package org.lanqiao.reflect;

import java.lang.reflect.*;
/**
 * 改变域成员的值
 * @author zw
 *
 */
public class field2 {
	public double d;

	public static void main(String args[]) {
		try {
			Class cls = Class.forName("field2");
			Field fld = cls.getField("d");
			field2 f2obj = new field2();//目标对象
			System.out.println("d = " + f2obj.d);
			fld.setDouble(f2obj, 12.34);
			System.out.println("d = " + f2obj.d);
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}

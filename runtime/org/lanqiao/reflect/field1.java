package org.lanqiao.reflect;

import java.lang.reflect.*;

/**
 * 获取域成员信息
 * 
 * @author zw
 * 
 */
public class field1 {
	private double d;
	public static final int i = 37;
	String s = "testing";

	public static void main(String args[]) {
		try {
			Class cls = Class.forName("com.bbk.j2ee.ch12.field1");

			Field fieldlist[] = cls.getDeclaredFields();
			for (int i = 0; i < fieldlist.length; i++) {
				Field fld = fieldlist[i];
				System.out.println("name = " + fld.getName());
				System.out.println("decl class = " + fld.getDeclaringClass());
				System.out.println("type = " + fld.getType());
				int mod = fld.getModifiers();
				System.out.println("modifiers = " + Modifier.toString(mod));
				System.out.println("-----");
			}
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}

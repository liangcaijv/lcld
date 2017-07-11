package org.lanqiao.reflect;

import java.lang.reflect.*;

/**
 * 获取域成员信�?
 * 
 * @author zw
 * 
 */
public class FieldInfo_04 {
	private double d;
	public static final int i = 37;
	String s = "testing";

	public static void main(String args[]) {
		try {
			Class cls = FieldInfo_04.class;

			Field fieldlist[] = cls.getDeclaredFields();
			for (int i = 0; i < fieldlist.length; i++) {
				Field fld = fieldlist[i];
				System.out.println("域成员名�?= " + fld.getName());
				System.out.println("�?���?= " + fld.getDeclaringClass());
				System.out.println("变量类型 = " + fld.getType());
				int mod = fld.getModifiers();
				System.out.println("修饰�?= " + Modifier.toString(mod));
				System.out.println("-----");
			}
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}

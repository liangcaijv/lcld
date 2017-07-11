package org.lanqiao.reflect;

import java.lang.reflect.*;
/**
 * 改变域成员的�?
 * @author zw
 *
 */
public class FieldReflectCall_07 {
	public double d;

	public static void main(String args[]) {
		try {
			Class cls = FieldReflectCall_07.class;
			Field fld = cls.getField("d");
			FieldReflectCall_07 f2obj = new FieldReflectCall_07();//目标对象
			System.out.println("d = " + f2obj.d);
			fld.setDouble(f2obj, 12.34);
			System.out.println("d = " + f2obj.d);
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}

package org.lanqiao.reflect;

import java.lang.reflect.*;
/**
 * 反射初步
 * @author zw
 *
 */
public class DumpMethods {
	public static void main(String args[]) {
		try {
			Class c = Class.forName("com.bbk.j2ee.ch11.DbUtil");
			Method m[] = c.getDeclaredMethods();//获取该Class的所有Method
			for (int i = 0; i < m.length; i++)
				System.out.println(m[i].toString());
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}
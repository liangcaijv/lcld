package org.lanqiao.reflect;

import java.lang.reflect.*;
/**
 * 获取方法信息
 * @author zw
 *
 */
public class method1 {
	private int f1(Object p, int x) throws NullPointerException {
		if (p == null)
			throw new NullPointerException();
		return x;
	}

	public static void main(String args[]) {
		try {
			Class cls = Class.forName("com.bbk.j2ee.ch12.method1");

			Method methlist[] = cls.getDeclaredMethods();
			for (int i = 0; i < methlist.length; i++) {
				Method m = methlist[i];
				System.out.println("name = " + m.getName());
				System.out.println("decl class = " + m.getDeclaringClass());
				Class pvec[] = m.getParameterTypes();
				for (int j = 0; j < pvec.length; j++)
					System.out.println("param #" + j + " " + pvec[j]);
				Class evec[] = m.getExceptionTypes();
				for (int j = 0; j < evec.length; j++)
					System.out.println("exception #" + j + " " + evec[j]);
				System.out.println("return type = " + m.getReturnType());
				System.out.println("-----");
			}
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}

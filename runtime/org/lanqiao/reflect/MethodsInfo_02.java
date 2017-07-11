package org.lanqiao.reflect;

import java.lang.reflect.*;

public class MethodsInfo_02 {

	public static void main(String args[]) {
		try {
			Class cls = Class.forName("java.lang.String");
			Method methlist[] = cls.getDeclaredMethods();
			for (int i = 0; i < methlist.length; i++) {
				Method m = methlist[i];
				System.out.println("方法�?= " + m.getName());
				System.out.println("�?���?= " + m.getDeclaringClass());
				Class pvec[] = m.getParameterTypes();
				for (int j = 0; j < pvec.length; j++)
					System.out.println("形参 #" + j + " " + pvec[j]);
				Class evec[] = m.getExceptionTypes();
				for (int j = 0; j < evec.length; j++)
					System.out.println("异常 #" + j + " " + evec[j]);
				System.out.println("返回类型  = " + m.getReturnType());
				System.out.println("-------------------------");
			}
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}

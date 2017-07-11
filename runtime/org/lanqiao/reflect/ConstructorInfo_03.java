/**
 * 
 */
package org.lanqiao.reflect;

import java.lang.reflect.*;

/**
 * @author zhengwei 2013-7-7
 * 
 */
@SuppressWarnings("rawtypes")
public class ConstructorInfo_03 {
	public static void main(String args[]) {
		try {
			Class cls = Class.forName("java.lang.String");

			Constructor ctorlist[] = cls.getDeclaredConstructors();
			for (int i = 0; i < ctorlist.length; i++) {
				Constructor ct = ctorlist[i];
				System.out.println("构�?器名 = " + ct.getName());
				System.out.println("�?���?= " + ct.getDeclaringClass());
				Class pvec[] = ct.getParameterTypes();
				for (int j = 0; j < pvec.length; j++)
					System.out.println("形参 #" + j + " " + pvec[j]);
				Class evec[] = ct.getExceptionTypes();
				for (int j = 0; j < evec.length; j++)
					System.out.println("异常 #" + j + " " + evec[j]);
				System.out.println("-----");
			}
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}

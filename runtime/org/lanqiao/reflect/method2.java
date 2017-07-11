package org.lanqiao.reflect;

import java.lang.reflect.*;
/**
 * 反射调用方法
 * @author zw
 *
 */
public class method2 {
	public int add(int a, int b) {
		return a + b;
	}

	public static void main(String args[]) {
		try {
			Class cls = Class.forName("com.bbk.j2ee.ch12.method2");
			Class partypes[] = new Class[2];
			partypes[0] = Integer.TYPE;
			partypes[1] = Integer.TYPE;
			//获取一个具体方法，需要提供方法名及参数类型列表
			Method meth = cls.getMethod("add", partypes);
			method2 methobj = new method2();
			Object arglist[] = new Object[2];
			arglist[0] = new Integer(37);
			arglist[1] = new Integer(47);
			//反射调用方法，需要提供目标对象及参数列表
			Object retobj = meth.invoke(methobj, arglist);
			Integer retval = (Integer) retobj;
			System.out.println(retval.intValue());
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}

package org.lanqiao.reflect;

import java.lang.reflect.*;
/**
 * 反射调用方法
 * @author zw
 *
 */
public class MethodReflectCall_05 {
	public int add(int a, int b) {
		return a + b;
	}

	public static void main(String args[]) {
		try {
			Class cls = MethodReflectCall_05.class;
			
			Class partypes[] = new Class[2];//参数类型列表
			partypes[0] = Integer.TYPE;
			partypes[1] = Integer.TYPE;
			//获取�?��具体方法，需要提供方法名及参数类型列�?
			Method meth = cls.getMethod("add", partypes);
			
			MethodReflectCall_05 methobj = new MethodReflectCall_05();

			//构�?参数列表
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

package org.lanqiao.reflect;

import java.lang.reflect.*;

public class ConstructorReflectCall_06 {
	public ConstructorReflectCall_06() {
	}

	public ConstructorReflectCall_06(int a, int b) {
		System.out.println("a = " + a + " b = " + b);
	}

	public static void main(String args[]) {
		try {
			Class cls = ConstructorReflectCall_06.class;
			Class partypes[] = new Class[2];
			partypes[0] = Integer.TYPE;
			partypes[1] = Integer.TYPE;
			//获取构�?器只�?��供参数类型列�?
			Constructor ct = cls.getConstructor(partypes);
			Object arglist[] = new Object[2];
			arglist[0] = new Integer(37);
			arglist[1] = new Integer(47);
			//调用构�?器创建新的实�?
			Object retobj = ct.newInstance(arglist);
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}

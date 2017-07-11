package org.lanqiao.reflect;

import java.lang.reflect.*;

public class constructor2 {
	public constructor2() {
	}

	public constructor2(int a, int b) {
		System.out.println("a = " + a + " b = " + b);
	}

	public static void main(String args[]) {
		try {
			Class cls = Class.forName("constructor2");
			Class partypes[] = new Class[2];
			partypes[0] = Integer.TYPE;
			partypes[1] = Integer.TYPE;
			//获取构造器只需提供参数类型列表
			Constructor ct = cls.getConstructor(partypes);
			Object arglist[] = new Object[2];
			arglist[0] = new Integer(37);
			arglist[1] = new Integer(47);
			//调用构造器创建新的实例
			Object retobj = ct.newInstance(arglist);
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}

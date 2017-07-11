package org.lanqiao.reflect;

import java.lang.reflect.*;
/**
 * 反射初步
 * —�?获取元数�?
 * @author zw
 *
 */
public class DumpMethods_01 {
	public static void main(String args[]) {
		try {
//			Class c = Class.forName("java.lang.String");//获取运行期类型信息的封装—�?描述了一个类型的结构的元数据
			Class c = String.class;//String类已经被加载到内存中
			c = Class.forName("ch12_io.FileStuff");//如果还未加载ch12_io.FileStuff，则会触发类的加�?
			
			
			Method[] m = c.getDeclaredMethods();//�?��方法的签�?
			for (int i = 0; i < m.length; i++)
				System.out.println(m[i].toString());//打印方法签名
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}
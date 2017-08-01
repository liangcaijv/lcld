package org.lanqiao.reflect;

import java.lang.reflect.*;
/**
 * 反射初步
 * 1、第一个要了解的是Class类型，它是类的类——代表了一个类的元数据
 * 2、一个Class实例，是虚拟机在加载一个类的时候自动产生的
 * 3、我们至少有3种方法来获取这个实例
 *  3.1  类型.class
 *  3.2  obj.getClass(); // obj是具体类型的实例
 *  3.3  Class.forName("类的全限定名字符串");
 * 前两者都需要该类型已被加载，第三种会触发jvm加载指定的类型，并返回Class的实例
 * @author zw
 *
 */
public class _01_ShowInfo {
	public static void main(String args[]) {
		try {
			Class<String> claz = String.class;
			print(claz.toGenericString());
			println("{");
			// 输出域成员
			Field[] fields = claz.getDeclaredFields();
			for (Field field : fields) {
        println("\t"+field.toGenericString());
      }
			println("=======构造器=========");
			Constructor[] constructors = claz.getDeclaredConstructors();
			for (Constructor constructor : constructors) {
        println("\t"+constructor.toGenericString());
      }
      println("=======方法=========");
			Method[] methods = claz.getDeclaredMethods();
			for (Method method : methods) {
        println("\t"+method.toGenericString());
      }
			print("}");
//			Method m[] = claz.getDeclaredMethods();//获取该Class的所有Method
//			for (int i = 0; i < m.length; i++)
//				System.out.println(m[i].toString());
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
	
	static void println(Object obj){
	  System.out.println(obj);
	}
	static void print(Object obj){
    System.out.print(obj);
  }
}
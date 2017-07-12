package org.lanqiao.java8;

import java.util.stream.Stream;

/**
 * 将方法作为参数来传递，C语言中有函数指针;Java中其实本质不同，效果相同
 * 传递一个lamda是传递一个接口的实例，只要一个方法形如函数式接口的抽象方法，那么可以用这个方法的引用来当作
 * lamda表达式或函数式接口的实例。
 * 特别地，构造器可以当作一个Supplier的实现
 * @author zhengwei lastmodified 2017年3月29日
 *
 */
public class D3_functionReference {
	
	
	public static void main(String[] args) {
		new Thread(D3_functionReference::run).start();
		Stream.generate(D3_functionReference::randomString).limit(5).forEach(e->{
			System.out.println(e);
		});
	}
	
	public static void run(){
		System.out.println("这是一个run方法");
	}
	
	public static String randomString(){
		
		return "&*^HKjkday";
		
	}
}

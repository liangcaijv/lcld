package org.lanqiao.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MethodRef {
	public static void main(String[] args) {
		test4();
  }
	/**
	 * 第一种引用 静态方法 
	 */

	private static void test1() {
	  List<Integer> ints = Arrays.asList(1, 2, 3);
		ints.sort(Integer::compare);
  }
	/**
	 * 第二种引用 某个特定对象的实例方法
	 */
	private static void test2(){
		List<String> words = Arrays.asList("apple", "banana", "pear");
		words.forEach(System.out::println);
	}
	/**
	 * 3 引用类的实例方法
	 */
	private static void test3(){
		List<String> words = Arrays.asList("apple", "banana", "pear");
		Stream<Integer> stream = words.stream().map(String::length); // method reference
		stream.forEach(System.out::println);
	}
	/**
	 * 4 引用类的构造方法
	 */
	private static void test4(){
		List<String> words = Arrays.asList("apple", "banana", "pear");
		words.stream().map(StringBuilder::new).forEach(System.out::println);
	}
	
}

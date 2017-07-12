package org.lanqiao.java8;

/**
 * 箭头函数的使用，java8中箭头函数并不是函数，实则是一个匿名内部类的行为描述
 * 本质是传递了匿名内部类的一个对象
 * @author zhengwei lastmodified 2017年3月29日
 *
 */
public class D1_basic {
	public static void main(String[] args) {
		f1();
	}

	/*匿名内部类-lamda
	 * 假如一个类（接口）只有一个方法，我们要使用这个类（接口）创建一个匿名子类，便可以使用lamda表达式
	 * 
	 * 
	 * */
	private static void f1() {
		new Thread(()->{
			System.out.println(1);
		}).start();
		
/*	等价形式
    new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println(1);
			}
		});
		*/
	}
}

package chapter05;

class Scope {

	public static void main(String args[]) {

		int x; // main方法内部的x变量

		x = 10;

		if (x == 10) { // 开始一个新的作用于

			int y = 20; // 尽在当前代码块内有效的变量y

			// x与y都可以访问
			System.out.println("x and y: " + x + " " + y);
			x = y * 2;

		}
		// y = 100; //错误! 已超出y的作用于
		// x 仍可以被访问
		System.out.println("x = " +x);
		
	}
}
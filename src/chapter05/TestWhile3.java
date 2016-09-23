package chapter05;

import java.util.Scanner;

class TestWhile3 {
	public static void main(String[] args) {
		// 使用字符串String存储密码，后面课程会详细介绍String类
		String userPass = ""; // 用户输入的密码
		final String PASSWORD = "123456"; // 正确密码为123456
		Scanner input = new Scanner(System.in);
		do {
			System.out.print("请输入程序密码：");
			userPass = input.nextLine(); // 从控制台获取用户输入的密码
			System.out.println();
			// 字符串的equals()方法用于判断两个字符串的值是否相同
		} while (!userPass.equals(PASSWORD)); // 密码输入不正确，继续循环，重新输入
		System.out.println("程序密码正确，继续执行！");
	}
}

package chapter05;

import java.util.Scanner;

//类声明
public class ParityCheckWhile {
	//主方法声明
	public static void main(String[] args) {
		//控制台内容捕获器
		Scanner scanner = new Scanner(System.in);
		//循环执行获取与判断
		while (true) {
			System.out.println("请输入一个整数");
			//等待获取控制台输入整数内容，并出入变量num
			int num = scanner.nextInt();
			//声明输出结果
			String result;
			//判断数字奇偶性（是否能被2整除），根据不同逻辑赋值给输出结果
			result = (num % 2 == 0 ? "这是一个偶数" : "这是一个奇数");
			//打印结果
			System.out.println(result);
		}
		
	}
}

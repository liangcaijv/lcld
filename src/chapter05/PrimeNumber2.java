package chapter05;

import java.util.Scanner;

class PrimeNumber2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要获取质数的范围..");
		int domain = scanner.nextInt();
		int i, j; // 声明循环参数
		outer: for (i = 2; i < domain; i++) { // 从2开始，逐个递增进行判断
			for (j = 2; j < i; j++) { // 从2开始，逐个递增到外循环的整数变量
				if (i % j == 0) // 外循环数除以内循环数，余0则非质数，跳出内循环
					continue outer; // 跳出内循环，跳到outer标识的位置继续循环
			}
			System.out.println(i); // 否则显示质数
		}
	}

}

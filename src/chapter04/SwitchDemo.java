package chapter04;

import java.util.Scanner;

/**
 * @TODO 根据不同会员等级计算商品价格
 * 		 1:白金会员 8折
 * 		 2:黄金会员 9折
 * 		 3:普通会员 9.5折
 * 		-1:非会员 原价
 */
public class SwitchDemo {
	public static void main(String[] args) {
		System.out.println("请输入会员等级");
		Scanner scanner = new Scanner(System.in);
		byte grade = scanner.nextByte();
		System.out.println("请输入商品价格");
		float price = scanner.nextFloat();
		switch (grade) {
		case 1:
			System.out.println("您好白金会员:这件商品的最终价格为"+(0.8*price));
			break;
		case 2:
			System.out.println("您好黄金会员:这件商品的最终价格为"+(0.9*price));
			break;
		case 3:
			System.out.println("您好普通会员:这件商品的最终价格为"+(0.95*price));
			break;
		default:
			System.out.println("您好顾客:这件商品的最终价格为"+price);
			break;
		}
	}
}

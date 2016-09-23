package chapter04;

import java.util.Scanner;

public class SwitchImpl {
	public static void main(String[] args) {
		//获取总金额
		Scanner scanner = new Scanner(System.in);
		float total = scanner.nextFloat();
		//声明结算价格
		float finalPrice;
		//价格分级，结果为总价格/500的整除结果
		int grade = (int)total/500;
		System.out.println("您的折扣等级为"+grade);
		switch (grade) {
		case 0:
			//0<总价格<500   	 结算金额=总价格*1.0
			finalPrice = total*1.0f;
			break;
		case 1:
			//500<总价格<1000	 结算金额=总价格*0.95
			finalPrice = total*0.95f;
			break;
		case 2:
		case 3:
			//1000<总价格<2000	 结算金额=总价格*0.88
			finalPrice = total*0.88f;
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			//2000<总价格<5000	 结算金额=总价格*0.8
			finalPrice = total*0.8f;
			break;
		default:
			//总价格>5000    	 结算金额=总价格*0.66
			finalPrice = total*0.66f;
			break;
		}
		
		System.out.println("消费的最终价格为"+finalPrice);
	}
}

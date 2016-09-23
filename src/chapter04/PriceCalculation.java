package chapter04;

import java.util.Scanner;

public class PriceCalculation {

	public static void main(String[] args) {
		//获取总金额
		Scanner scanner = new Scanner(System.in);
		float total = scanner.nextFloat();
		float finalPrice;
		if(total>0 && total<500){
			//0<总价格<500   	 	结算金额=总价格*1.0
			finalPrice = total*1.0f;
		}else if(total>=500 && total<1000){
			//500<总价格<1000	 	结算金额=总价格*0.95
			finalPrice = total*0.95f;
		}else if(total>=1000 && total<2000){
			//1000<总价格<2000	 结算金额=总价格*0.88
			finalPrice = total*0.88f;
		}else if(total>=2000 && total<5000){
			//2000<总价格<5000	 结算金额=总价格*0.8
			finalPrice = total*0.8f;
		}else if(total>=5000){
			//总价格>5000    	 结算金额=总价格*0.66
			finalPrice = total*0.66f;
		}else{
			System.out.println("价格输入有误!");
			return;
		}
		System.out.println("消费的最终价格为"+finalPrice);
	}
}

package chapter04;

import java.util.Scanner;

public class PriceCalculation {

	public static void main(String[] args) {
		//��ȡ�ܽ��
		Scanner scanner = new Scanner(System.in);
		float total = scanner.nextFloat();
		float finalPrice;
		if(total>0 && total<500){
			//0<�ܼ۸�<500   	 	������=�ܼ۸�*1.0
			finalPrice = total*1.0f;
		}else if(total>=500 && total<1000){
			//500<�ܼ۸�<1000	 	������=�ܼ۸�*0.95
			finalPrice = total*0.95f;
		}else if(total>=1000 && total<2000){
			//1000<�ܼ۸�<2000	 ������=�ܼ۸�*0.88
			finalPrice = total*0.88f;
		}else if(total>=2000 && total<5000){
			//2000<�ܼ۸�<5000	 ������=�ܼ۸�*0.8
			finalPrice = total*0.8f;
		}else if(total>=5000){
			//�ܼ۸�>5000    	 ������=�ܼ۸�*0.66
			finalPrice = total*0.66f;
		}else{
			System.out.println("�۸���������!");
			return;
		}
		System.out.println("���ѵ����ռ۸�Ϊ"+finalPrice);
	}
}

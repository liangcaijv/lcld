package chapter04;

import java.util.Scanner;

public class SwitchImpl {
	public static void main(String[] args) {
		//��ȡ�ܽ��
		Scanner scanner = new Scanner(System.in);
		float total = scanner.nextFloat();
		//��������۸�
		float finalPrice;
		//�۸�ּ������Ϊ�ܼ۸�/500���������
		int grade = (int)total/500;
		System.out.println("�����ۿ۵ȼ�Ϊ"+grade);
		switch (grade) {
		case 0:
			//0<�ܼ۸�<500   	 ������=�ܼ۸�*1.0
			finalPrice = total*1.0f;
			break;
		case 1:
			//500<�ܼ۸�<1000	 ������=�ܼ۸�*0.95
			finalPrice = total*0.95f;
			break;
		case 2:
		case 3:
			//1000<�ܼ۸�<2000	 ������=�ܼ۸�*0.88
			finalPrice = total*0.88f;
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			//2000<�ܼ۸�<5000	 ������=�ܼ۸�*0.8
			finalPrice = total*0.8f;
			break;
		default:
			//�ܼ۸�>5000    	 ������=�ܼ۸�*0.66
			finalPrice = total*0.66f;
			break;
		}
		
		System.out.println("���ѵ����ռ۸�Ϊ"+finalPrice);
	}
}

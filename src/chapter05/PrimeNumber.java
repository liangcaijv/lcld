package chapter05;

import java.util.Scanner;

class PrimeNumber {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		boolean isPrimeNumber = true;
		for (int i = 2; i < num; i++) {
			if(num>1&&num%i==0){
				System.out.println("�ܱ�"+i+"����");
				isPrimeNumber = false;
				break;
			}
		}
		if(isPrimeNumber){
			System.out.println(num+"������");
		}else{
			System.out.println(num+"��������");
		}
	}
}

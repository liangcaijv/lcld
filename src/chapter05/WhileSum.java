package chapter05;

import java.util.Scanner;

public class WhileSum {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int sum = 0;
		int control = 10;//¿ØÖÆ
		while(control > 0){
			int num = scanner.nextInt();
			sum+=num; //Í¬sum = sum+num;
			control++;//Í¬control=control+1;
		}
		System.out.println();
	}
}

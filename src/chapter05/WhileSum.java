package chapter05;

import java.util.Scanner;

public class WhileSum {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int sum = 0;
		int control = 10;//����
		while(control > 0){
			int num = scanner.nextInt();
			sum+=num; //ͬsum = sum+num;
			control++;//ͬcontrol=control+1;
		}
		System.out.println();
	}
}

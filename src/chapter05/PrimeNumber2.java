package chapter05;

import java.util.Scanner;

class PrimeNumber2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������Ҫ��ȡ�����ķ�Χ..");
		int domain = scanner.nextInt();
		int i, j; // ����ѭ������
		outer: for (i = 2; i < domain; i++) { // ��2��ʼ��������������ж�
			for (j = 2; j < i; j++) { // ��2��ʼ�������������ѭ������������
				if (i % j == 0) // ��ѭ����������ѭ��������0���������������ѭ��
					continue outer; // ������ѭ��������outer��ʶ��λ�ü���ѭ��
			}
			System.out.println(i); // ������ʾ����
		}
	}

}

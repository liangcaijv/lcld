package chapter05;

import java.util.Scanner;

//������
public class ParityCheckWhile {
	//����������
	public static void main(String[] args) {
		//����̨���ݲ�����
		Scanner scanner = new Scanner(System.in);
		//ѭ��ִ�л�ȡ���ж�
		while (true) {
			System.out.println("������һ������");
			//�ȴ���ȡ����̨�����������ݣ����������num
			int num = scanner.nextInt();
			//����������
			String result;
			//�ж�������ż�ԣ��Ƿ��ܱ�2�����������ݲ�ͬ�߼���ֵ��������
			result = (num % 2 == 0 ? "����һ��ż��" : "����һ������");
			//��ӡ���
			System.out.println(result);
		}
		
	}
}

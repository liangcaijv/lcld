package chapter05;

import java.util.Scanner;

//������
public class ParityCheckWhile2 {
	//����������
	public static void main(String[] args) {
		//����̨���ݲ�����
		Scanner scanner = new Scanner(System.in);
		int loopControl = 0;//ѭ�����Ʊ���
		while (loopControl < 10) {
			//�ȴ���ȡ����̨�����������ݣ����������num
			int num = scanner.nextInt();
			//����������
			String result;
			//�ж�������ż�ԣ��Ƿ��ܱ�2�����������ݲ�ͬ�߼���ֵ��������
			result = (num % 2 == 0 ? "����һ��ż��" : "����һ������");
			//��ӡ���
			System.out.println(result);
			loopControl--;//ÿִ��һ��ѭ���壬���Ʊ�����1��ͬloopControl=loopControl+1;
		}
		
	}
}

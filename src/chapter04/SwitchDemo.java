package chapter04;

import java.util.Scanner;

/**
 * @TODO ���ݲ�ͬ��Ա�ȼ�������Ʒ�۸�
 * 		 1:�׽��Ա 8��
 * 		 2:�ƽ��Ա 9��
 * 		 3:��ͨ��Ա 9.5��
 * 		-1:�ǻ�Ա ԭ��
 */
public class SwitchDemo {
	public static void main(String[] args) {
		System.out.println("�������Ա�ȼ�");
		Scanner scanner = new Scanner(System.in);
		byte grade = scanner.nextByte();
		System.out.println("��������Ʒ�۸�");
		float price = scanner.nextFloat();
		switch (grade) {
		case 1:
			System.out.println("���ð׽��Ա:�����Ʒ�����ռ۸�Ϊ"+(0.8*price));
			break;
		case 2:
			System.out.println("���ûƽ��Ա:�����Ʒ�����ռ۸�Ϊ"+(0.9*price));
			break;
		case 3:
			System.out.println("������ͨ��Ա:�����Ʒ�����ռ۸�Ϊ"+(0.95*price));
			break;
		default:
			System.out.println("���ù˿�:�����Ʒ�����ռ۸�Ϊ"+price);
			break;
		}
	}
}

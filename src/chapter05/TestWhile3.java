package chapter05;

import java.util.Scanner;

class TestWhile3 {
	public static void main(String[] args) {
		// ʹ���ַ���String�洢���룬����γ̻���ϸ����String��
		String userPass = ""; // �û����������
		final String PASSWORD = "123456"; // ��ȷ����Ϊ123456
		Scanner input = new Scanner(System.in);
		do {
			System.out.print("������������룺");
			userPass = input.nextLine(); // �ӿ���̨��ȡ�û����������
			System.out.println();
			// �ַ�����equals()���������ж������ַ�����ֵ�Ƿ���ͬ
		} while (!userPass.equals(PASSWORD)); // �������벻��ȷ������ѭ������������
		System.out.println("����������ȷ������ִ�У�");
	}
}

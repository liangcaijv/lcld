package chapter04;

import java.util.Scanner;

//������
public class ParityCheckIf {
	//����������
		public static void main(String[] args) {
			//����̨���ݲ�����
			Scanner scanner = new Scanner(System.in);
			//�ȴ���ȡ����̨�����������ݣ����������num
			int num = scanner.nextInt();
			//����������
			String result;
			//�ж�������ż�ԣ��Ƿ��ܱ�2�����������ݲ�ͬ�߼���ֵ��������
			if(num%2==0){
				result = "����һ��ż��";
			}else{
				result = "����һ������";
			}
			//��ӡ���
			System.out.println(result);
		}
}

package chapter05;

public class BubbleSort {
	public static void main(String[] args) {
		int[] a = new int[]{88,76,90,89,70,95,99,85,66,89};
		int temp;
		//����ĳ��ȿ���ͨ����������.length�����
		for (int i = 0; i < a.length-1; i++) {			//��Ҫ�Ƚ�n-1��
			for (int j = 0; j < a.length-i-1 ; j++) {	//����a.length-i-1��ÿ����Ҫ�ȽϵĴ������ּ���1��
				if (a[j] > a[j+1]) {			//���������бȽϣ��������������滻
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
	}
	
}


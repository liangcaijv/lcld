package chapter06;

/**
 * ���������㷨ʵ��
 */
public class InsertSort {
	public static void main(String[] args) {
		int[] arr = new int[] { 1, 5, 3, 6, 9, 2, 4, 7, 5, 8 };
		// ������ָ������������������
		int temp;// ����������
		int j; // �������� ���һ��Ԫ�ص��±�
		// ѭ����ȡÿһ���������Ԫ��
		for (int i = 1; i < arr.length; i++) {
			temp = arr[i];// a[i]��Ϊ�����������
			// ���������еĵ�һ�ʼ���˴����������бȽϣ�Ϊ�������� ����λ�� ������λ��
			for (j = i - 1; j >= 0; j--) {
				System.out.println("������Ԫ��" + temp + " ,�������е���" + j);
				if (temp < arr[j]) {
					// �����λ����temp�Ƴ��ռ�
					arr[j + 1] = arr[j];
				} else {
					break;// �����ڱ�
				}
			}
			arr[j + 1] = temp;// ��ʱtemp�Ѿ��ҵ��Լ���λ��
		}
	}
}

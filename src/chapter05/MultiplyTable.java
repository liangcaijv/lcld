package chapter05;

public class MultiplyTable {
	public static void main(String[] args) {
		for (int i = 1; i <= 9; i++) {
			//��������
			for (int j = 1; j <= i; j++) {
				//�ڲ����������
				System.out.print(j + "x" + i + "=" + i * j + "\t");
			}
			System.out.println();
		}
	}
}
package chapter05;

public class Rectangle2 {

	public static void main(String[] args) {
		//�����͸�
		int height;
		int width=10;
		//�������
		//ȷ��Ҫ���������
		for (height = 8; height>=0; height--) {
			for (int widthControl = width; widthControl >= 0; widthControl--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}

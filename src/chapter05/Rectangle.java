package chapter05;

public class Rectangle {

	public static void main(String[] args) {
		//�����͸�
		int width = 10;
		int height = 8;
		//�������
		//ȷ��Ҫ���������
		while(height >= 0){
			//ȷ��ÿһ���������
			int widthControl = width;
			while(widthControl>=0){
				System.out.print("*");
				widthControl--;
			}
			System.out.println();
			height--;
		}
	}
}

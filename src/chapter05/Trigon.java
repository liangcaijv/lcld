package chapter05;

public class Trigon {

	public static void main(String[] args) {
		int side = 10;//�����α߳�
		int row = 0;//��ӡ���Ƶ�����
		while(row <= side){
			int column = row;
			while(column>=0){
				System.out.print("*");
				column--;
			}
			row++;
			System.out.println();
		}
	}
}

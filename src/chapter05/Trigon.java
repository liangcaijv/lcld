package chapter05;

public class Trigon {

	public static void main(String[] args) {
		int side = 10;//三角形边长
		int row = 0;//打印控制的行数
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

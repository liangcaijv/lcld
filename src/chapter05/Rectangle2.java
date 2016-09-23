package chapter05;

public class Rectangle2 {

	public static void main(String[] args) {
		//定义宽和高
		int height;
		int width=10;
		//逐行输出
		//确定要输出多少行
		for (height = 8; height>=0; height--) {
			for (int widthControl = width; widthControl >= 0; widthControl--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}

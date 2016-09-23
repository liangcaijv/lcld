package chapter05;

public class Rectangle {

	public static void main(String[] args) {
		//定义宽和高
		int width = 10;
		int height = 8;
		//逐行输出
		//确定要输出多少行
		while(height >= 0){
			//确定每一行输出内容
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

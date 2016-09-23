package chapter05;

public class MultiplyTable {
	public static void main(String[] args) {
		for (int i = 1; i <= 9; i++) {
			//外层输出行
			for (int j = 1; j <= i; j++) {
				//内层输出行内容
				System.out.print(j + "x" + i + "=" + i * j + "\t");
			}
			System.out.println();
		}
	}
}
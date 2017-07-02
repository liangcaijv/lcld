import java.util.Scanner;

public class Case4_5_SacnnerDemo{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// System.out.println("本程序产生随机整数和浮点数，请输入两个整数作为范围，数字之间空格或换行隔开：" );
		// int a  = in.nextInt();// 将会阻塞当前线程，等待用户的输入
		// int b = in.nextInt();// 将会阻塞当前线程，等待用户的输入
		// // 产生[a,b]随机整数
		// // a + random()*(b-a+1)
		// int r = a + (int)(Math.random()*(b-a+1))	;
		// double d = a + Math.random()*(b-a+1)	;
		// System.out.println("产生的随机整数是：" + r);
		// System.out.println("产生的随机浮点数是：" + d);

		System.out.println("本程序计算圆的面积，请输入圆的半径：" );
		double r  = in.nextDouble();// 将会阻塞当前线程，等待用户的输入
		double area = Math.PI * r * r;
		System.out.println("圆的面积是：" + area);
	}
}
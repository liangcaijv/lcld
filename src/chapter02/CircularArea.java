package chapter02;

import java.util.Scanner;

/**
 * @描述：获取控制输入圆的半径，输出圆的面积
 */
public class CircularArea {

	public static void main(String[] args) {
		
		final double PI = 3.14;//声明圆周率常量，double为实数类型
		
		//PI = 3.1415926;//试图修改常量，编译时会报出编译错误!
		
		double r;//声明存储圆半径变量
		
		Scanner scanner = new Scanner(System.in);//控制台内容获取器
		
		r = scanner.nextDouble();//此行代码会让控制台等待输入一个实数，输入后键入回车会把输入值赋值给r变量
		
		double area = PI*r*r;//声明圆面积变量，并引用圆周率变量PI和半径变量r计算后赋值
		
		System.out.println("半径为"+r+"的圆的面积为：" + area);
		
	}

}

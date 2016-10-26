package chapter06;

public class StaticDemo {

	private static int NUMSTATIC = 0;

	private int num;

	public void sum() {
		NUMSTATIC++;// 非静态的方法可以访问静态成员
		num++;// 可以访问非静态的成员
		sumStatic();
	}

	public static void sumStatic() {
		NUMSTATIC++;
//		num++;
//		sum();
		sumStatic2();
	}

	public static void sumStatic2() {

	}
}
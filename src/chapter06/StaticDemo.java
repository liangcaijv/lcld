package chapter06;

public class StaticDemo {

	private static int NUMSTATIC = 0;

	private int num;

	public void sum() {
		NUMSTATIC++;// �Ǿ�̬�ķ������Է��ʾ�̬��Ա
		num++;// ���Է��ʷǾ�̬�ĳ�Ա
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
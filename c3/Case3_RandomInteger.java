public class Case3_RandomInteger{
	public static void main(String[] args) {
		int a ;
		int b = 1000;
		// 产生[a,b]随机整数
		// a + random()*(b-a+1)
		int r = a + (int)(Math.random()*(b-a+1))	;
		double d = a + Math.random()*(b-a+1)	;
		System.out.println("产生的随机整数是：" + r);
		System.out.println("产生的随机浮点数是：" + d);
	}
}
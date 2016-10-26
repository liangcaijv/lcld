package chapter06;

public class StaticVar {

	private static int NUMSTATIC=0;
	
	private int num=0;
	
	public void add(){
		num++;
		System.out.println("当前成员变量num为" + num);
	}
	
	public static void addStatic(){
		NUMSTATIC++;
		System.out.println("当前静态变量NUMSTATIC为" + NUMSTATIC);
	}
	
	public static void main(String[] args) {
		StaticVar test = new StaticVar();
		test.add();
		test.add();
		StaticVar test1 = new StaticVar();
		test1.add();
		StaticVar.addStatic();
		test.addStatic();
		test1.addStatic();
	}
}

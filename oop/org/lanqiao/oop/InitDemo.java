package org.lanqiao.oop;

public class InitDemo {
	
	int a;
	{	
		a=this.b;
	}
	int b=20;

	public InitDemo() {
		b=30;
	}
	
	public static void main(String[] args) {
		System.out.println(new InitDemo().b);
	}
}

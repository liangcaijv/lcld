class MethodDemo {
	void f(){
		System.out.println("f is called......");
	}
	static void g(){
		System.out.println("g is called......");
	}
	
	static void h(int i,String s){
		System.out.println("h is called......i=="+i+",s=="+s);
	}
	
	static int m(int i,String s){
		System.out.println("h is called......i=="+i+",s=="+s);
		return i*10;
	}
	public static void main(String[] args){
		//MethodDemo demo = new MethodDemo();
		//demo.f();
		
		g();
		h(10,"sing");
		
		int x = m(10,"xyz");
		int y = 20+m(10,"xyz");
	}
}
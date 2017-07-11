package org.lanqiao.oop.innerclasses;


public class Test {
	public static void main(String[] args) {
		IE e = new A().new B().new C().new D().new E();
		e.f();
	}
}
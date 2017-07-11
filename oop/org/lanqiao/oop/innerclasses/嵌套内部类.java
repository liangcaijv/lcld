package org.lanqiao.oop.innerclasses;

class A{
	String name="123";
	class B{
		class C{
			String name = "567";
			class D{
				String name = "234";
				class E implements IE{	
					public void f(){
						System.out.println(C.this.name);
					}
				}
			}
		}
	}
}
interface IE{
	void f();
}
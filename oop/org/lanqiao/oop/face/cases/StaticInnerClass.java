package org.lanqiao.oop.face.cases;

import org.lanqiao.oop.extend.cases.IDict;

public class StaticInnerClass {
	private String f1;
	static String f2;
	//��̬�ڲ���
	static class ADict implements IDict{

		public String cn2En(String en) {
			//System.out.println(f1);//�������:Cannot make a static reference to the non-static field f1
			System.out.println(f2);//ok
			return null;
		}

		@Override
		public String enToCn(String cnString) {
			return null;
		}
		public static void f(){}
	}
	class A{
//		�������:The field ff cannot be declared static; static fields can only be declared in static or top level types
//		static String ff;
	}
	public static void main(String[] args) {
		IDict dict = new StaticInnerClass.ADict();
		dict.cn2En("hi");
	}
}

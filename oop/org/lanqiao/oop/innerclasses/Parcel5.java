package org.lanqiao.oop.innerclasses;

public class Parcel5 {
	public Destination3 dest(final String s) {
		final String name="";
//		class PDestination implements Destination {
//			private String label;
//			private PDestination(String whereTo) {
//				label = whereTo;
//			}
//			public String readLabel() { return label; }
//		}
//		return new PDestination(s);
//		匿名内部类不可以有构造参数
		return  new Destination3() {

			@Override
			public String readLabel() {
				return name;//使用到了s，s是外部参数，必须为final
			}
		};
	}
	public static void main(String[] args) {
		Parcel4 p = new Parcel4();
		Destination3 d = p.dest("Tanzania");
	}
} ///:~
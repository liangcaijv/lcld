package org.lanqiao.oop.innerclasses.un;

public class Parcel4_ {
	public Destination dest(final String s) {
//		class PDestination implements Destination {
//			private String label;
//			private PDestination(String whereTo) {
//				label = whereTo;
//			}
//			public String readLabel() { return label; }
//		}
//		return new PDestination(s);
//		匿名内部类不可以有构造参数
		return  new Destination() {

			@Override
			public String readLabel() {
				return s;//使用到了s，s是外部参数，必须为final
			}
		};
	}
	public static void main(String[] args) {
		Parcel4_ p = new Parcel4_();
		Destination d = p.dest("Tanzania");
	}
} ///:~
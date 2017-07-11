package org.lanqiao.oop.innerclasses.un;

public class Parcel4___ {
	private String name;
	public Destination dest(final String s) {
//		class PDestination implements Destination {
//			private String label;
//			private PDestination(String whereTo) {
//				label = whereTo;
//			}
//			public String readLabel() { return label; }
//		}
//		return new PDestination(s);
		String name="123";
//		匿名内部类不可以有构造参数
		return  new Destination() {

			@Override
			public String readLabel() {
//				System.out.println(Parcel4__.this.name);//显式使用外围类域成员
				return s;//使用到了s，s是外部参数，必须为final
			}
		};
	}
	public static void main(String[] args) {
		Parcel4__ p = new Parcel4__();
		Destination d = p.dest("Tanzania");
	}
} ///:~
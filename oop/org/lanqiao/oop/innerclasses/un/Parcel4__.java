package org.lanqiao.oop.innerclasses.un;

//�����������ڵ���
public class Parcel4__ {
	public Destination dest(String s) {
		class PDestination implements Destination {
			private String label;
			private PDestination(String whereTo) {
				label = whereTo;
			}
			public String readLabel() { return label; }
		}
		return new PDestination(s);
	}
	public static void main(String[] args) {
		Parcel4__ p = new Parcel4__();
		Destination d = p.dest("Tanzania");
	}
} ///:~
package org.lanqiao.oop.innerclasses;

//�����������ڵ���
public class Parcel4 {
	public Destination3 dest(String s) {
		class PDestination implements Destination3 {
			private String label;
			private PDestination(String whereTo) {
				label = whereTo;
			}
			public String readLabel() { return label; }
		}
		return new PDestination(s);
	}
	public static void main(String[] args) {
		Parcel4 p = new Parcel4();
		Destination3 d = p.dest("Tanzania");
	}
} ///:~
package org.lanqiao.oop.innerclasses;

public class Parcel6 {
	private String name="123";
	public Destination3 dest(final String s) {

		return  new Destination3() {
			String name = "234";
			@Override
			public String readLabel() {
				System.out.println(Parcel6.this.name);
				return s;
			}
		};
	}
	public static void main(String[] args) {
		Parcel6 p = new Parcel6();
		Destination3 d = p.dest("Tanzania");
		d.readLabel();
	}
} ///:~
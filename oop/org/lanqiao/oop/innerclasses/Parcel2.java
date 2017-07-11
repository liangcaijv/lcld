package org.lanqiao.oop.innerclasses;

public class Parcel2 {
	class Contents {
		private int i = 11;
		public int value() { return i; }
	}
	class Destination2 {
		private String label;
		Destination2(String whereTo) {
			label = whereTo;
		}
		String readLabel() { return label; }
	}
	//�������������ڲ���ʵ��
	public Destination2 to(String s) {
		return new Destination2(s);
	}
	public Contents cont() {
		return new Contents();
	}

	public static void main(String[] args) {
		Parcel2 q = new Parcel2();
		
		Parcel2.Contents c = q.cont();
		
		Parcel2.Destination2 d = q.to("Borneo");
	}
} ///:~
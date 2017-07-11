package org.lanqiao.oop.innerclasses;

abstract class Contents {
	abstract public int value();
}
interface Destination3 {
	String readLabel();
}
public class Parcel3 {
	private class PContents1234 extends Contents {
		private int i = 11;
		public int value() { return i; }
	}
	private class PContents extends Contents {
		private int i = 234;
		public int value() { return i; }
	}
	private class haha implements Destination3 {
		private String label;
		private haha(String whereTo) {
			label = whereTo;
		}
		public String readLabel() { return label; }
	}
	//��������ת������
	public Destination3 dest(String s) {
		return new haha(s);
	}
	public Contents cont() {
		return new PContents();
	}
}
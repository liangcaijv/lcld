package org.lanqiao.oop.innerclasses.un;

abstract class Contents {
	abstract public int value();
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
	protected class PDestination implements Destination {
		private String label;
		private PDestination(String whereTo) {
		label = whereTo;
		}
		public String readLabel() { return label; }
	}
	//��������ת������
	public Destination dest(String s) {
		return new PDestination(s);
	}
	public Contents cont() {
		return new PContents();
	}
}
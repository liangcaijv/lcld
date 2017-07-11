package org.lanqiao.oop.innerclasses.un;

public class Parcel7 {
	String name;
	private static class MyDetination implements Destination{
		String name;
		@Override
		public String readLabel() {
			return name;
		}
	}
	public static void main(String[] args) {
		
		Destination d = new Parcel7.MyDetination();
	}
} ///:~

//A
//
//B
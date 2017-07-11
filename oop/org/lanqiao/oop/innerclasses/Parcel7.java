package org.lanqiao.oop.innerclasses;

public class Parcel7 {
	String name;
	private static class MyDetination implements Destination3{
		String name;
		@Override
		public String readLabel() {
			return name;
		}
	}
	public static void main(String[] args) {
		
		Destination3 d = new Parcel7.MyDetination();
	}
} ///:~

//A
//
//B
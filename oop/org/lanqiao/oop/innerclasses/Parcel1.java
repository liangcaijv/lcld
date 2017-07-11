package org.lanqiao.oop.innerclasses;

/**
�������ڲ���Ķ����ʹ��
**/
public class Parcel1 {
	class Contents {
		private int i = 11;
		public int value(){ 
			return i; 
		}
	}
	class Destination1 {
		private String label;
		Destination1(String whereTo) {//�ڲ���Ĺ�����
			label = whereTo;
		}
		String readLabel() { 
			return label; 
		}
	}
	// Using inner classes looks just like
	// using any other class, within Parcel1:
	public void ship(String dest) {
		Contents c = this.new Contents();
		Destination1 d = this.new Destination1(dest);
	}
	public static void main(String[] args) {
		Parcel1 p = new Parcel1();
		p.ship("Tanzania");
		
		Contents con = p.new Contents();
		
	}
} ///:~
package chapter06.main;

import chapter06.Poker4;

public class GameCenter {
	
	public static void main(String[] args) {
		
		int[] player1;//���1������Ϸ
		int[] player2;//���2������Ϸ
		int[] player3;//���3������Ϸ
		//��ʼ���ƶ�,׼��Ϊ���1��2��3����
		Poker4.init();
		player1 = Poker4.deal(3);//���1��3����
		player2 = Poker4.deal(3);//���2��3����
		player3 = Poker4.deal(3);//���3��3����
		
		int[] player4;//���4������Ϸ
		int[] player5;//���5������Ϸ
		int[] player6;//���6������Ϸ
		
		//��ʼ���ƶ�,׼��Ϊ���4��5��6����
		Poker4.init();
		
		player4 = Poker4.deal(3);//���4��3����
		player5 = Poker4.deal(3);//���5��3����
		player6 = Poker4.deal(3);//���6��3����
		
		//��ʱ��player1���ƣ���Ҫ���ƶ���1����
		int [] player1NewCard = Poker4.deal(1);
	}
}

package chapter06;

public class Poker5 {

	// �ƶѴ�ʱΪ��Ա�����������ڲ���ÿ������������˱���
	private int[] cards;

	public void init() {
		// Ϊ�ƶѳ�Ա������ֵ
	}

	public int[] deal(int count) {
		return null;
	}

	public static void main(String[] args) {
		
		//�����ƾֶ���game1
		Poker5 game1 = new Poker5();
		
		int[] player1;// ���1������Ϸ
		int[] player2;// ���2������Ϸ
		int[] player3;// ���3������Ϸ
		// ��ʼ��game1�ƶ�,׼��Ϊ���1��2��3����
		game1.init();
		player1 = game1.deal(3);// ���1��3����
		player2 = game1.deal(3);// ���2��3����
		player3 = game1.deal(3);// ���3��3����

		int[] player4;// ���4������Ϸ
		int[] player5;// ���5������Ϸ
		int[] player6;// ���6������Ϸ

		//�½��ƾֶ���game2
		Poker5 game2 = new Poker5();
		// ��ʼ��game2�ƶ�,׼��Ϊ���4��5��6����
		game2.init();

		player4 = game2.deal(3);// ���4��3����
		player5 = game2.deal(3);// ���5��3����
		player6 = game2.deal(3);// ���6��3����

		// ��ʱ��player1���ƣ���Ҫ��game1�ƶ���1����
		int[] player1NewCard = game1.deal(1);
	}
}

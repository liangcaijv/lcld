package chapter06;

import java.util.Arrays;

public class Poker2 {
	public static void main(String[] args) {
		//�˿�������
		int[] cards = new int[54];
		//�˿��Ƹ�ֵ
		for (int i = 0; i < cards.length; i++) {
			cards[i] = i+1;
		}
		//�����������
		int[][] players = new int[3][17];
		
		//Ϊplayer1����
		for (int i = 0; i < players.length; i++) {
			
			//�������±����ɳ�������±�ֵ��Ϊ-1��������-1�������������������
			for (int j = 0; j < players[i].length; ) {
				int ram = (int) (Math.random() * (53+1));;//��ȡ0-53��������ɸ���ң������ȸ�һ�����ƣ�Ҳ�������ɣ�
				if(cards[ram]>0){
					//�������±����ɳ�������±�ֵ��Ϊ-1��������-1�������������������
					players[i][j] = cards[ram];
					cards[ram] = -1;
					j++;
				}
			}
			System.out.println("player"+(i+1)+"������" + Arrays.toString(players[i]));
		}
		
		//�������
		for (int i = 0; i < cards.length; i++) {
			if(cards[i]!=-1){
				System.out.print(cards[i]+",");
			}
		}
	}
}

package chapter06;

import java.util.Arrays;

public class Poker {
	public static void main(String[] args) {
		//�˿�������
		int[] cards = new int[54];
		//�˿��Ƹ�ֵ
		for (int i = 0; i < cards.length; i++) {
			cards[i] = i+1;
		}
		//�����������
		int[] player1 = new int[17];
		int[] player2 = new int[17];
		int[] player3 = new int[17];
		
		//Ϊplayer1����
		for (int i = 0; i < player1.length; ) {
			
			int ram = (int) (Math.random() * (53+1));;//��ȡ0-53��������ɸ���ң������ȸ�һ�����ƣ�Ҳ�������ɣ�
			if(cards[ram]>0){
				//�������±����ɳ�������±�ֵ��Ϊ-1��������-1�������������������
				player1[i] = cards[ram];
				cards[ram] = -1;
				i++;
			}
		}
		
		//Ϊplayer2����
		for (int i = 0; i < player2.length; ) {
			//����
			int ram = (int) (Math.random() * (53+1));
			if(cards[ram]>0){
				player2[i] = cards[ram];
				cards[ram] = -1;
				i++;
			}
		}
		
		//Ϊplayer3����
		for (int i = 0; i < player3.length;) {
			//����
			int ram = (int) (Math.random() * (53+1));
			if(cards[ram]>0){
				player3[i] = cards[ram];
				cards[ram] = -1;
				i++;
			}
		}
		
		//������������
		System.out.println(Arrays.toString(player1));
		System.out.println(Arrays.toString(player2));
		System.out.println(Arrays.toString(player3));
		//�������
		for (int i = 0; i < cards.length; i++) {
			if(cards[i]!=-1){
				System.out.print(cards[i]+",");
			}
		}
	}
}

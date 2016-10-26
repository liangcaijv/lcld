package chapter06;

import java.util.Arrays;

public class Poker {
	public static void main(String[] args) {
		//扑克牌声明
		int[] cards = new int[54];
		//扑克牌赋值
		for (int i = 0; i < cards.length; i++) {
			cards[i] = i+1;
		}
		//玩家手牌声明
		int[] player1 = new int[17];
		int[] player2 = new int[17];
		int[] player3 = new int[17];
		
		//为player1发牌
		for (int i = 0; i < player1.length; ) {
			
			int ram = (int) (Math.random() * (53+1));;//获取0-53随机数，派给玩家（可以先给一家派牌，也可轮流派）
			if(cards[ram]>0){
				//如果随机下标已派出，则该下标值置为-1，如遇到-1，重新生成随机数派牌
				player1[i] = cards[ram];
				cards[ram] = -1;
				i++;
			}
		}
		
		//为player2发牌
		for (int i = 0; i < player2.length; ) {
			//发牌
			int ram = (int) (Math.random() * (53+1));
			if(cards[ram]>0){
				player2[i] = cards[ram];
				cards[ram] = -1;
				i++;
			}
		}
		
		//为player3发牌
		for (int i = 0; i < player3.length;) {
			//发牌
			int ram = (int) (Math.random() * (53+1));
			if(cards[ram]>0){
				player3[i] = cards[ram];
				cards[ram] = -1;
				i++;
			}
		}
		
		//输出各玩家手牌
		System.out.println(Arrays.toString(player1));
		System.out.println(Arrays.toString(player2));
		System.out.println(Arrays.toString(player3));
		//输出底牌
		for (int i = 0; i < cards.length; i++) {
			if(cards[i]!=-1){
				System.out.print(cards[i]+",");
			}
		}
	}
}

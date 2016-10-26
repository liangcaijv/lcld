package chapter06;

import java.util.Arrays;

public class Poker2 {
	public static void main(String[] args) {
		//扑克牌声明
		int[] cards = new int[54];
		//扑克牌赋值
		for (int i = 0; i < cards.length; i++) {
			cards[i] = i+1;
		}
		//玩家手牌声明
		int[][] players = new int[3][17];
		
		//为player1发牌
		for (int i = 0; i < players.length; i++) {
			
			//如果随机下标已派出，则该下标值置为-1，如遇到-1，重新生成随机数派牌
			for (int j = 0; j < players[i].length; ) {
				int ram = (int) (Math.random() * (53+1));;//获取0-53随机数，派给玩家（可以先给一家派牌，也可轮流派）
				if(cards[ram]>0){
					//如果随机下标已派出，则该下标值置为-1，如遇到-1，重新生成随机数派牌
					players[i][j] = cards[ram];
					cards[ram] = -1;
					j++;
				}
			}
			System.out.println("player"+(i+1)+"的手牌" + Arrays.toString(players[i]));
		}
		
		//输出底牌
		for (int i = 0; i < cards.length; i++) {
			if(cards[i]!=-1){
				System.out.print(cards[i]+",");
			}
		}
	}
}

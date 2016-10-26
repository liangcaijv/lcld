package chapter06.main;

import chapter06.Poker4;

public class GameCenter {
	
	public static void main(String[] args) {
		
		int[] player1;//玩家1加入游戏
		int[] player2;//玩家2加入游戏
		int[] player3;//玩家3加入游戏
		//初始化牌堆,准备为玩家1，2，3发牌
		Poker4.init();
		player1 = Poker4.deal(3);//玩家1发3张牌
		player2 = Poker4.deal(3);//玩家2发3张牌
		player3 = Poker4.deal(3);//玩家3发3张牌
		
		int[] player4;//玩家4加入游戏
		int[] player5;//玩家5加入游戏
		int[] player6;//玩家6加入游戏
		
		//初始化牌堆,准备为玩家4，5，6发牌
		Poker4.init();
		
		player4 = Poker4.deal(3);//玩家4发3张牌
		player5 = Poker4.deal(3);//玩家5发3张牌
		player6 = Poker4.deal(3);//玩家6发3张牌
		
		//此时，player1叫牌，需要从牌堆派1张牌
		int [] player1NewCard = Poker4.deal(1);
	}
}

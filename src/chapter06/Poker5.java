package chapter06;

public class Poker5 {

	// 牌堆此时为成员变量，在类内部，每个方法都共享此变量
	private int[] cards;

	public void init() {
		// 为牌堆成员变量赋值
	}

	public int[] deal(int count) {
		return null;
	}

	public static void main(String[] args) {
		
		//构建牌局对象game1
		Poker5 game1 = new Poker5();
		
		int[] player1;// 玩家1加入游戏
		int[] player2;// 玩家2加入游戏
		int[] player3;// 玩家3加入游戏
		// 初始化game1牌堆,准备为玩家1，2，3发牌
		game1.init();
		player1 = game1.deal(3);// 玩家1发3张牌
		player2 = game1.deal(3);// 玩家2发3张牌
		player3 = game1.deal(3);// 玩家3发3张牌

		int[] player4;// 玩家4加入游戏
		int[] player5;// 玩家5加入游戏
		int[] player6;// 玩家6加入游戏

		//新建牌局对象game2
		Poker5 game2 = new Poker5();
		// 初始化game2牌堆,准备为玩家4，5，6发牌
		game2.init();

		player4 = game2.deal(3);// 玩家4发3张牌
		player5 = game2.deal(3);// 玩家5发3张牌
		player6 = game2.deal(3);// 玩家6发3张牌

		// 此时，player1叫牌，需要从game1牌堆派1张牌
		int[] player1NewCard = game1.deal(1);
	}
}

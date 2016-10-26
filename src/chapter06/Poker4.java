package chapter06;

public class Poker4 {
	
	//牌堆此时为成员变量，在类内部，每个方法都共享此变量
	private static int[] cards;
	
	public static void init(){
		//为牌堆成员变量赋值
		cards = new int[54];
		//扑克牌赋值
		for (int i = 0; i < cards.length; i++) {
			cards[i] = i+1;
		}
	}
	
	public static int[] deal(int count){
		//根据指定数量声明手牌数组
		int[] resultCards = new int[count];
		
		for (int i = 0; i < resultCards.length;) {
			int ram = (int) (Math.random() * (54));//获取0-53随机数，代表在牌堆内的随机位置
			//cards[ram]即为随机抽取的牌，-1代表此牌已发，所以仅当随机位置的牌大于0时，此牌方可发出。
			if (cards[ram] > 0) {
				//如果随机下标已派出，则该下标值置为-1，如遇到-1，重新生成随机数派牌
				resultCards[i] = cards[ram];
				cards[ram] = -1;//已发的牌，在牌堆内置为-1。
				i++;
			} 
		}
		//返回已发手牌
		return resultCards;
	}
	
	public static void main(String[] args) {
		init();
	}
}

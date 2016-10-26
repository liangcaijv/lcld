package chapter06;

public class Poker3 {
	
	/**
	 * @todo 初始化牌堆
	 * @return 并得到一副牌，标号1-54
	 */
	public static int[] init(){
		//扑克牌声明
		int[] cards = new int[54];
		//扑克牌赋值
		for (int i = 0; i < cards.length; i++) {
			cards[i] = i+1;
		}
		return cards;
	}
	
	/**
	 * @todo 发指定数量的牌
	 * @param cards 扑克牌堆
	 * @param count 要发牌的数量
	 * @return 发牌后得到的手牌
	 */
	public static int[] deal(int[] cards,int count){
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
	
	
	public static void printColor(int card){

		String result ="";
		int color = card/13;
		switch (color) {
		case 0:
			result = "黑桃";
			break;
		case 1:
			result = "红桃";
			break;
		case 2:
			result = "梅花";
			break;
		case 3:
			result = "方片";
			break;
		default:
			result = "错误花色代码";
			break;
		}
			
		System.out.println("手牌花色为" + result);
	
	}
	
	public static void printPoint(int card){
		int point = card%13;
		String result ="";
		switch (point) {
		case 1:
			result = "A";
			break;
		case 11:
			result = "J";
			break;
		case 12:
			result = "Q";		
			break;
		case 13:
			result = "K";
			break;
		default:
			result = (point+"");//非AKQJ的点数直接就为数字，由于point是int，加一个空字符串""自动转型为String
			break;
		}
		System.out.println("手牌点数为" + result);
	}
	
	/**
	 * 封装通过扑克代码，得到扑克点数和花色的方法
	 * @param card 扑克代码
	 * @return 扑克的花色和点数的字符串表示
	 */
	public static String cardInfo(int card){
		
		if(card == 53){
			return "小鬼";
		}
		
		if(card == 54){
			return "大鬼";
		}
		
		String result ="";	//声明要返回的结果字符串
		
		int color = card/13;//花色代码
		int point = card%13;//点数代码
		
		//分析花色
		switch (color) {
		case 0:
			result = "黑桃";
			break;
		case 1:
			result = "红桃";
			break;
		case 2:
			result = "梅花";
			break;
		case 3:
			result = "方片";
			break;
		default:
			return "错误的代码";
		}
		
		//分析点数，点数直接与得到的花色result拼接
		switch (point) {
		case 1:
			result += "A";
			break;
		case 11:
			result += "J";
			break;
		case 12:
			result += "Q";		
			break;
		case 13:
			result += "K";
			break;
		default:
			result += point;//非AKQJ的点数直接就为数字
			break;
		}
		return result;
	}
	
	public static String cardInfo(int[] cards){
		String result = "";
		//循环扑克数组
		for (int i = 0; i < cards.length; i++) {
			int currentCard = cards[i];//当前遍历的扑克代码
			//调用已完成的cardInfo方法，获取当前扑克信息
			String currentCardInfo = cardInfo(currentCard);
			result +=currentCardInfo;//追加到结果字符串
			if(i != cards.length-1){
				//非末位，加一个,分隔符
				result+=",";
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] cards = init();//初始化一副手牌
		int[] player1Cards = deal(cards, 17);//把手牌传入发牌方法，进行发牌
		int[] player2Cards = deal(cards, 17);//把手牌传入发牌方法，进行发牌
		int[] player3Cards = deal(cards, 17);//把手牌传入发牌方法，进行发牌
	}
}

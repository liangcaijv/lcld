package chapter06;

public class Poker3 {
	
	/**
	 * @todo ��ʼ���ƶ�
	 * @return ���õ�һ���ƣ����1-54
	 */
	public static int[] init(){
		//�˿�������
		int[] cards = new int[54];
		//�˿��Ƹ�ֵ
		for (int i = 0; i < cards.length; i++) {
			cards[i] = i+1;
		}
		return cards;
	}
	
	/**
	 * @todo ��ָ����������
	 * @param cards �˿��ƶ�
	 * @param count Ҫ���Ƶ�����
	 * @return ���ƺ�õ�������
	 */
	public static int[] deal(int[] cards,int count){
		//����ָ������������������
		int[] resultCards = new int[count];
		
		for (int i = 0; i < resultCards.length;) {
			int ram = (int) (Math.random() * (54));//��ȡ0-53��������������ƶ��ڵ����λ��
			//cards[ram]��Ϊ�����ȡ���ƣ�-1��������ѷ������Խ������λ�õ��ƴ���0ʱ�����Ʒ��ɷ�����
			if (cards[ram] > 0) {
				//�������±����ɳ�������±�ֵ��Ϊ-1��������-1�������������������
				resultCards[i] = cards[ram];
				cards[ram] = -1;//�ѷ����ƣ����ƶ�����Ϊ-1��
				i++;
			} 
		}
		//�����ѷ�����
		return resultCards;
	}
	
	
	public static void printColor(int card){

		String result ="";
		int color = card/13;
		switch (color) {
		case 0:
			result = "����";
			break;
		case 1:
			result = "����";
			break;
		case 2:
			result = "÷��";
			break;
		case 3:
			result = "��Ƭ";
			break;
		default:
			result = "����ɫ����";
			break;
		}
			
		System.out.println("���ƻ�ɫΪ" + result);
	
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
			result = (point+"");//��AKQJ�ĵ���ֱ�Ӿ�Ϊ���֣�����point��int����һ�����ַ���""�Զ�ת��ΪString
			break;
		}
		System.out.println("���Ƶ���Ϊ" + result);
	}
	
	/**
	 * ��װͨ���˿˴��룬�õ��˿˵����ͻ�ɫ�ķ���
	 * @param card �˿˴���
	 * @return �˿˵Ļ�ɫ�͵������ַ�����ʾ
	 */
	public static String cardInfo(int card){
		
		if(card == 53){
			return "С��";
		}
		
		if(card == 54){
			return "���";
		}
		
		String result ="";	//����Ҫ���صĽ���ַ���
		
		int color = card/13;//��ɫ����
		int point = card%13;//��������
		
		//������ɫ
		switch (color) {
		case 0:
			result = "����";
			break;
		case 1:
			result = "����";
			break;
		case 2:
			result = "÷��";
			break;
		case 3:
			result = "��Ƭ";
			break;
		default:
			return "����Ĵ���";
		}
		
		//��������������ֱ����õ��Ļ�ɫresultƴ��
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
			result += point;//��AKQJ�ĵ���ֱ�Ӿ�Ϊ����
			break;
		}
		return result;
	}
	
	public static String cardInfo(int[] cards){
		String result = "";
		//ѭ���˿�����
		for (int i = 0; i < cards.length; i++) {
			int currentCard = cards[i];//��ǰ�������˿˴���
			//��������ɵ�cardInfo��������ȡ��ǰ�˿���Ϣ
			String currentCardInfo = cardInfo(currentCard);
			result +=currentCardInfo;//׷�ӵ�����ַ���
			if(i != cards.length-1){
				//��ĩλ����һ��,�ָ���
				result+=",";
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] cards = init();//��ʼ��һ������
		int[] player1Cards = deal(cards, 17);//�����ƴ��뷢�Ʒ��������з���
		int[] player2Cards = deal(cards, 17);//�����ƴ��뷢�Ʒ��������з���
		int[] player3Cards = deal(cards, 17);//�����ƴ��뷢�Ʒ��������з���
	}
}

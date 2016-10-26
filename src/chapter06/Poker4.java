package chapter06;

public class Poker4 {
	
	//�ƶѴ�ʱΪ��Ա�����������ڲ���ÿ������������˱���
	private static int[] cards;
	
	public static void init(){
		//Ϊ�ƶѳ�Ա������ֵ
		cards = new int[54];
		//�˿��Ƹ�ֵ
		for (int i = 0; i < cards.length; i++) {
			cards[i] = i+1;
		}
	}
	
	public static int[] deal(int count){
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
	
	public static void main(String[] args) {
		init();
	}
}

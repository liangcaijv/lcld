package chapter02;

/**
 * @���� ��֪Jack�����Ϊ180cm��rose��Jack��10cm,lucy��rose��8cm��������˵���ߵ�����̨
 */
public class MathApplication {

	public static void main(String[] args) {
		int heightOfJack = 180;//����Jack��߱���
		int heightOfLucy = heightOfJack - 10;//����lucy��ߣ�����jack��߱���������
		int heightOfRose = heightOfLucy + 8;//����Rose��ߣ�����Lucy��߱���������
		System.out.println("Jack������ǣ�" + heightOfJack + "cm");//���Jack���
		System.out.println("lucy������ǣ�" + heightOfLucy + "cm");//���Lucy���
		System.out.println("rose������ǣ�" + heightOfRose + "cm");//���Rose���
	}

}

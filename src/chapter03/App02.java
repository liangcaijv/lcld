package chapter03;

public class App02 {
	public static void main(String[] args) {
		//��Ʒԭ��=�ۺ���ۼ۳����ۿ۱���
		double originalPrice = 52/0.8;//�۸�����С��λ�ģ������ø�������������,������float������������Ч��
		System.out.println("��Ʒԭ��Ϊ" + originalPrice);
		//�ɱ��۸�=����/�ɱ���۱�
		double costPrice = originalPrice/(1+0.3);
		System.out.println("��Ʒ�ĳɱ�Ϊ��"+costPrice);
		//75�ۺ�۸�-�ɱ�=��Ʒ����
		double profit = (int)(originalPrice*0.75)-costPrice;//originalPrice*0.75�Ľ��Ϊdouble��ǿ��ת��Ϊint����ȥС��λ��ע�ⲻ���������룩��
		System.out.println("75�ۺ����Ʒ����Ϊ��" + profit);
		
	}
}

package chapter03;

public class Ap {
	public static void main(String[] args) {
		
		String name = "����"; 					//������String����(�ַ���)��ʾ��ע��String���ǻ����������ͣ��мǣ���
		byte birthMonth = 9; 					//�����·ݣ�byte����(��������)  ��Χ ��-128~127
		short birthYear = 1964;					//������ݣ�short����(������) ��Χ�� -32768~32767
		int height = 170;      					//��ߣ�int(��������)  ��Χ��-2,147,483,648~2,147,483,647
		long money = 1500000000000l;			//�ʲ���long(������)��Χ�� -9,223,372,036,854,775,808~9,223,372,036,854,775,807
		float weight = 55.3f;					//���أ�float(�����ȸ�����);С����󾫶�Ϊ6~7λ
		double goldRingPurity = 0.99999999999999;	//���ָ���ȣ�double(˫���ȸ�����);С�����ľ���Ϊ15~16λ
		char gender = '��';						//�Ա�char(�ַ�����)���ܱ�ʾһ���ַ�(���ġ�Ӣ�ġ����š����־���);
		boolean married = true; 				//�ѻ飺boolean(��������)��������true��false
		
		System.out.println(0.12345678901234567890);
		System.out.println(0.12345678901234567890f);
	}
}

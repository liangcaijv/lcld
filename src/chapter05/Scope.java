package chapter05;

class Scope {

	public static void main(String args[]) {

		int x; // main�����ڲ���x����

		x = 10;

		if (x == 10) { // ��ʼһ���µ�������

			int y = 20; // ���ڵ�ǰ���������Ч�ı���y

			// x��y�����Է���
			System.out.println("x and y: " + x + " " + y);
			x = y * 2;

		}
		// y = 100; //����! �ѳ���y��������
		// x �Կ��Ա�����
		System.out.println("x = " +x);
		
	}
}
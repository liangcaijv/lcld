package chapter03;

class TestConvert {
	public static void main(String[] args) {
		int i1 = 222;
		int i2 = 333;
		double d1 = (i1 + i2) * 2.9; // ϵͳ��ת��Ϊdouble������
		float f1 = (float) ((i1 + i2) * 2.9); // ��double��ת����float�ͣ���Ҫ����ǿ������ת��
		System.out.println(d1);
		System.out.println(f1);

		byte b1 = 88;
		byte b2 = 99;
		byte b3 = (byte) (b1 + b2); // ϵͳ��ת��Ϊint�����㣬�ٴ�int��ת����byte��
									// ��Ҫ����ǿ������ת��
		System.out.println("88 + 99 = " + b3); // ǿ������ת�������ݽ�����

		double d2 = 5.1E88;
		float f2 = (float) d2; // ��double��ǿ��ת����float�ͣ�������
		System.out.println(f2);

		float f3 = 3.14F;
		f3 = f3 + 0.05F;// ������䲻��д��f3 = f3 + 0.05;������ᱨ����Ϊ0.05��double�ͣ�
						// ����f3����Ȼ��double�ͣ�����float�ᱨ��
		System.out.println("3.14F + 0.05F = " + f3);
	}
}

package chapter03;

class TestConvert {
	public static void main(String[] args) {
		int i1 = 222;
		int i2 = 333;
		double d1 = (i1 + i2) * 2.9; // 系统将转换为double型运算
		float f1 = (float) ((i1 + i2) * 2.9); // 从double型转换成float型，需要进行强制类型转换
		System.out.println(d1);
		System.out.println(f1);

		byte b1 = 88;
		byte b2 = 99;
		byte b3 = (byte) (b1 + b2); // 系统先转换为int型运算，再从int型转换成byte型
									// 需要进行强制类型转换
		System.out.println("88 + 99 = " + b3); // 强制类型转换，数据结果溢出

		double d2 = 5.1E88;
		float f2 = (float) d2; // 从double型强制转换成float型，结果溢出
		System.out.println(f2);

		float f3 = 3.14F;
		f3 = f3 + 0.05F;// 这条语句不能写成f3 = f3 + 0.05;，否则会报错，因为0.05是double型，
						// 加上f3，仍然是double型，赋给float会报错
		System.out.println("3.14F + 0.05F = " + f3);
	}
}

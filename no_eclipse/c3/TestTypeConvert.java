/*
示范类型转换
绝对安全的赋值，即便两侧的数据类型不一致，也会发生自动类型转换
编译期只检查语法，运行期才负责运算
*/
public class TestTypeConvert{
	public static void main(String[] args){
		byte b = 127;
		int i = 129;
		long longNum = i;
		i = (int)longNum;  // 不强制转换，将得到编译错误

		float f = 123.123f; // 不加f，将会得到编译错误
		f = (float)(f + 1.2); // 不强制转换，将会得到一个编译错误

		double doubleNum = longNum;
		float floatNum = longNum;
	}

}
package chapter06;

class ScopeErr {

	public static void main(String args[]) {
		int bar = 1;
		{
			// 创建一个新作用域
//			int bar = 2;	// 编译错误： error "C bar already defined!
		}

	}
}
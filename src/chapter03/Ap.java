package chapter03;

public class Ap {
	public static void main(String[] args) {
		
		String name = "马云"; 					//姓名：String类型(字符串)表示，注意String不是基本数据类型，切记！！
		byte birthMonth = 9; 					//出生月份：byte类型(超短整型)  范围 ：-128~127
		short birthYear = 1964;					//出生年份：short类型(短整型) 范围： -32768~32767
		int height = 170;      					//身高：int(整数类型)  范围：-2,147,483,648~2,147,483,647
		long money = 1500000000000l;			//资产：long(长整型)范围： -9,223,372,036,854,775,808~9,223,372,036,854,775,807
		float weight = 55.3f;					//体重：float(单精度浮点型);小数点后精度为6~7位
		double goldRingPurity = 0.99999999999999;	//金戒指纯度：double(双精度浮点型);小数点后的精度为15~16位
		char gender = '男';						//性别：char(字符类型)，能表示一个字符(中文、英文、符号、数字均可);
		boolean married = true; 				//已婚：boolean(布尔类型)，仅能是true或false
		
		System.out.println(0.12345678901234567890);
		System.out.println(0.12345678901234567890f);
	}
}

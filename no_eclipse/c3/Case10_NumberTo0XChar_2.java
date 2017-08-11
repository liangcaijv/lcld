import java.util.Scanner;

public class Case10_NumberTo0XChar_2 {
	public static void main(String[] args) {
		// 定一个特殊场景，然后算出结果
		// 15 --> F  (char)15? (char)69='F'
		// 大于9的情况 'A'+(x-10)
		// 小于9的情况 ""+x
		Scanner in = new Scanner(System.in);
		System.out.println("请输入0~15的一个整数：" );
		int x  = in.nextInt();
			
		// char result = (char)(x>9 ? ( 'A'+(x-10) ):( '0'+(x-0) ));
		// 改写成if-else语法
		char result;
		if(x>=0 && x<=15){
			if(x>9){
				result = (char)('A'+(x-10));
			}else{
				result = (char)('0'+(x-0));
			}
			System.out.println(x+"的十六进制表示为：" + result);
		}else{
			System.out.println("这个数整不了……");
		}
	}
}
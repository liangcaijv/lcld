/*ʾ���ݹ�*/
public class Recursion{
	public static void main(String[] args){
		System.out.println(feb(80));
	}
	
	static int add(int n){
		if(n==1)
			return 1;
		else
			return n+add(n-1);
	}
	
	static long feb(int n){
		if(n<=2)
			return 1;
		else
			return feb(n-1)+feb(n-2);
	}
	
}
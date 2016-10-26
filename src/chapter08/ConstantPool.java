package chapter08;

public class ConstantPool {

	public static void main(String[] args) {
		String s1 = new String("aaa");//s1指向堆内存创建的新空间
		String s2 = new String("aaa");//s2指向堆内存创建的新空间
		String s3 = "aaa";//查找常量池是否有"aaa"如果有s3指向，没有创建
		String s4 = "aaa";//查找常量池是否有"aaa"如果有s4指向，没有创建
		System.out.println(s1 == s2);//false,s1和s2的中引用的地址是不同的空间
		System.out.println(s3 == s4);//true,s1和s2引用的同时常量池的相同数据空间
		System.out.println(s1 == s3);//false,s1指向堆空间，s3指向常量池
		
		String name = "James";
		boolean result = name instanceof Object;
		System.out.println(result);
	}
}

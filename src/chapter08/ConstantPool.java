package chapter08;

public class ConstantPool {

	public static void main(String[] args) {
		String s1 = new String("aaa");//s1ָ����ڴ洴�����¿ռ�
		String s2 = new String("aaa");//s2ָ����ڴ洴�����¿ռ�
		String s3 = "aaa";//���ҳ������Ƿ���"aaa"�����s3ָ��û�д���
		String s4 = "aaa";//���ҳ������Ƿ���"aaa"�����s4ָ��û�д���
		System.out.println(s1 == s2);//false,s1��s2�������õĵ�ַ�ǲ�ͬ�Ŀռ�
		System.out.println(s3 == s4);//true,s1��s2���õ�ͬʱ�����ص���ͬ���ݿռ�
		System.out.println(s1 == s3);//false,s1ָ��ѿռ䣬s3ָ������
		
		String name = "James";
		boolean result = name instanceof Object;
		System.out.println(result);
	}
}

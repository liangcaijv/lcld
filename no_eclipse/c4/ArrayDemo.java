public class ArrayDemo{
	int i;
	int[] intArray = new int[10];
	String[] strArr  = new String[3];
	// byte[] btArr = new byte[]{2,3,6,4,8};//��̬��ʼ��
	byte[] btArr = new byte[5];
	void init(){//�����ʼ��
		for(int i=0;i<10;i++){//��ѭ����ÿ��Ԫ�ظ�ֵ
			intArray[i] = i;//ȡ����Ԫ�أ��±��0��ʼ
		}
		intArray[8] = 88;	
		
		strArr[0]="zhangsan";
		strArr[1]="lisi";
		strArr[2]="wangwu";
	}
	//��������
	void test(){
		for(int i=0;i<intArray.length;i++){
			System.out.print(intArray[i]+"\t");
		}
		
		System.out.print("\n");
		
		for(int i=0;i<strArr.length;i++){
			System.out.print(strArr[i]+"\t");
		}
	}
	//��ǿforѭ����������
	void test2(){
		for(int e:intArray){
			System.out.print(e+"\t");
		}
		
		System.out.print("\n");
		
		for(String e:strArr){
			System.out.print(e+"\t");
		}
	}
	public static void main(String[] args){
		//static ������������������ͨ���������ø���Ķ�������
		// int i=10;
		ArrayDemo demo = new ArrayDemo();
		// demo.init();
		demo.test();
		
	}
}
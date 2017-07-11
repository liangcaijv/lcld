public class ArrayDemo{
	int i;
	int[] intArray = new int[10];
	String[] strArr  = new String[3];
	// byte[] btArr = new byte[]{2,3,6,4,8};//静态初始化
	byte[] btArr = new byte[5];
	void init(){//数组初始化
		for(int i=0;i<10;i++){//用循环对每个元素赋值
			intArray[i] = i;//取数组元素，下标从0开始
		}
		intArray[8] = 88;	
		
		strArr[0]="zhangsan";
		strArr[1]="lisi";
		strArr[2]="wangwu";
	}
	//遍历数组
	void test(){
		for(int i=0;i<intArray.length;i++){
			System.out.print(intArray[i]+"\t");
		}
		
		System.out.print("\n");
		
		for(int i=0;i<strArr.length;i++){
			System.out.print(strArr[i]+"\t");
		}
	}
	//增强for循环遍历数组
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
		//static 方法用类名来调，普通方法必须用该类的对象来调
		// int i=10;
		ArrayDemo demo = new ArrayDemo();
		// demo.init();
		demo.test();
		
	}
}
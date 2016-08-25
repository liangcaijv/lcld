package chapter05;

public class BubbleSort {
	public static void main(String[] args) {
		int[] a = new int[]{88,76,90,89,70,95,99,85,66,89};
		int temp;
		//数组的长度可以通过“数组名.length”获得
		for (int i = 0; i < a.length-1; i++) {			//需要比较n-1轮
			for (int j = 0; j < a.length-i-1 ; j++) {	//根据a.length-i-1，每轮需要比较的次数逐轮减少1次
				if (a[j] > a[j+1]) {			//相邻数进行比较，符合条件进行替换
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
	}
	
}


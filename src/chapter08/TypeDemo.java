package chapter08;

import java.util.Arrays;

public class TypeDemo {

	/**
	 * 
	 * @param arr ����������
	 */
	public void sort(int[] arr){
		//����ð�����򷨣�����������������ﶼ�Ƕ�ԭ������в���
		int temp;
		for (int i = 0; i < arr.length-1; i++) {			
			for (int j = 0; j < arr.length-i-1 ; j++) {	
				if (arr[j] > arr[j+1]) {			
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}

	/**
	 * ����һ�����֣�����ֵ�������Ϊ������ƽ��
	 */
	public void square(int num){
		num = num*num;
	}
	
	public static void main(String[] args) {
		TypeDemo demo = new TypeDemo();
		int[] a = {11,33,12,9,7,1,6,10};
		demo.sort(a);
		System.out.println(Arrays.toString(a));
		
		int n = 10;
		demo.square(n);
		System.out.println(n);
	}
	
}

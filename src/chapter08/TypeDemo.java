package chapter08;

import java.util.Arrays;

public class TypeDemo {

	/**
	 * 
	 * @param arr 待排序数组
	 */
	public void sort(int[] arr){
		//利用冒泡排序法，对数组进行排序，这里都是对原数组进行操作
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
	 * 传入一个数字，并赋值这个数字为该数的平方
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

package org.lanqiao.concurrent.basic;

import java.util.Random;


/**
 * 本例示范join方法的效果
 * 
 * @author zhengwei last modified:2012-4-30
 * 
 */
public class JoinThreads {

	public static void main(String[] args) {
		WorkerThread[] threads = new WorkerThread[10];
		int[][] bigMatrix = getBigHairyMatrix();
		int max = Integer.MIN_VALUE;
		
		// Give each thread a slice of the matrix to work with
		for (int i = 0; i < 10; i++) {
			threads[i] = new WorkerThread(bigMatrix[i]);
			threads[i].start();
		}
		// 等待一个线程完成之后再调用getMax获取它计算出来的最大值，否则可能得到的是一个中间值
		try {
			for (int i = 0; i < 10; i++) {
				threads[i].join();
				max = Math.max(max, threads[i].getMax());
			}
		} catch (InterruptedException e) {
			// fall through
		}
		System.out.println("Maximum value was " + max);
	}
	//获得一个10*10的二维数组
	private static int[][] getBigHairyMatrix() {
		int[][] arr = new int[10][10];
		for(int i=0;i<10;i++){
			for(int j = 0;j<10;j++){
				arr[i][j] = new Random().nextInt(100);
			}
		}
		return arr;
	}

	private static class WorkerThread extends Thread {
		int max = Integer.MIN_VALUE;
		int[] ourArray;

		public WorkerThread(int[] ourArray) {
			this.ourArray = ourArray;
		}

		// 在一维数组中查找最大值
		public void run() {
			for (int i = 0; i < ourArray.length; i++)
				max = Math.max(max, ourArray[i]);
		}

		public int getMax() {
			return max;
		}
	}
}

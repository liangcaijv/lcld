package org.lanqiao.concurrent.sync;

/**
 * 本例示范对暴露在多线程可存取的数据不加限制的情况下可能出现的奇怪现象
 * @author zhengwei last modified:2012-4-30
 *
 */
public class InConsistent {
	public static void main(String[] args) {
		new T1().start();
		new T2().start();
	}
	
	static int i=0;
	
	private static class T1 extends Thread{
		@Override
		public void run() {
			while(i<10){
				try {
					Thread.sleep(1000);//模拟程序处理某些逻辑的耗时
					System.out.println("这个小于10的数据是："+i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private static class T2 extends Thread{
		@Override
		public void run() {
			while(i<100){
				i++;
			}
		}
	}
}

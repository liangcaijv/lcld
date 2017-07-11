package org.lanqiao.concurrent;

/**
 * 本例示范synchronized关键字的基本用法
 * @author zhengwei last modified:2012-4-30
 *
 */
public class SynchronizedDemo {
	public static void main(String[] args) {
		new T1().start();
		new T2().start();
	}
	static int i=0;
	static final Object lock = new Object();
	private static class T1 extends Thread{
		@Override
		public void run() {
			synchronized (lock) {//执行区块代码的前提是获得lock上的监视器——排他的
				while(i<10){
					try {
						Thread.sleep(1000);
						System.out.println("这个小于10的数据是："+i++);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	private static class T2 extends Thread{
		@Override
		public void run() {
			synchronized (lock) {
				while(i<100){
					i++;
				}
			}

		}
	}
}

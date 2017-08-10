package org.lanqiao.concurrent.basic;

/**
 * 本例示范死锁现象
 * @author zhengwei last modified:2012-5-2
 *
 */
public class DeadLock {
	public static void main(String[] args) {
		new T1().start();
		new T2().start();
	}
	
	
	private static Object obj1 = new Object();
	private static Object obj2 = new Object();
	private static class T1 extends Thread{
		@Override
		public void run() {
			synchronized (obj1) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
				synchronized (obj2) {
					System.out.println("俺是线程1……");
				}
			}
		}
	}
	private static class T2 extends Thread{
		@Override
		public void run() {
			synchronized (obj2) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
				synchronized (obj1) {
					System.out.println("俺是线程2……");
				}
			}
		}
	}
}

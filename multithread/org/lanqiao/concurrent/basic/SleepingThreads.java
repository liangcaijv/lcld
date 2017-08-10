package org.lanqiao.concurrent.basic;

/**
 * 本例示范Thread.sleep()的作用及其打破阻塞的方法
 * 
 * @author zhengwei last modified:2012-4-30
 * 
 */
public class SleepingThreads {
	public static void main(String[] args) {
		CountThread t = new CountThread();
		t.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}

		t.interrupt();//注释掉这行，CountThread逢10等待10s；
		/* 取消注释后，这句代码执行时CountThread如果在阻塞中，阻塞状态会被打破，
		 * 其sleep将抛出InterruptedException */
	}

	static class CountThread extends Thread {
		private boolean flag = true;
		private int i = 0;

		public void stopIt() {
			flag = false;
		}

		@Override
		public void run() {
			while (flag) {
				try {
					if (i % 10 == 0)
						Thread.sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("不等了……");
				}
				System.out.println(i++);
			}
		}
	}
}

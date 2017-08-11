package org.lanqiao.concurrent.basic;

/**
 * 示范多线程的创建及启动
 * 多运行几次，看看结果  ^_^
 * @author zhengwei last modified:2012-4-28
 * 
 */
public class Threads{
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread1();
		Thread t2 = new Thread(new Thread2());
		// 匿名内部类方式
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程3");
			}
		});

		t1.start();
		t2.start();
		t3.start();

		Thread.sleep(50);// 主线程休眠50ms

		((Thread1) t1).stopIt();
	}
}

class Thread1 extends Thread {
	private boolean flag = true;

	public Thread1() {
		super("线程1");
	}

	public void stopIt() {
		flag = false;
	}

	@Override
	public void run() {
		while (flag) {
			System.out.println(this.getName());
		}
	}
}

class Thread2 implements Runnable {
	String name = "线程2";

	@Override
	public void run() {
		System.out.println(this.name);
	}
}
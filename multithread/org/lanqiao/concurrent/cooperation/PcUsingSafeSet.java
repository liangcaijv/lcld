package org.lanqiao.concurrent.cooperation;

import java.util.*;

/**
 * 生产者和消费者问题
 * 
 * @author zheng last modified:2012-7-23
 * 
 */
public class PcUsingSafeSet {
	public static void main(String[] args) {
		final Hamburgers hamburgers = new Hamburgers();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					hamburgers.add(new Random().nextInt());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					hamburgers.remove();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					hamburgers.remove();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread t4 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					hamburgers.remove();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t1.setName("厨师");
		t2.setName("吃货1");
//		t3.setName("吃货2");
//		t4.setName("吃货3");
		
		t1.start();
		t2.start();
//		t3.start();
//		t4.start();
	}
}

class Hamburgers {
	private List<Integer> list = new ArrayList<Integer>();

	public void add(int i) {
		synchronized (list) {
			if (list.size() < 10) {
				System.out.println(Thread.currentThread().getName() + "添加汉堡包"
						+ i);
				list.add(i);
				list.notifyAll();
			} else{
				try {
					list.wait();//当前线程挂起，并放弃list上锁，直到别的线程调用list.notifyAll(),才会重新进入竞争锁的行列
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void remove() {
		synchronized (list) {
			if (list.size() > 0) {
				System.out.println(Thread.currentThread().getName() + "吃掉汉堡包"
						+ list.remove(0));
				list.notifyAll();
			}else{
				try {
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
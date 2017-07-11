package org.lanqiao.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 本例示范生产者-消费者问题的解决方案
 * 
 * @author heigong
 * 
 */
public class ProdCons {
	public static void main(String[] args) {
		new Producer().start();
		new Consumer().start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	private static final List<Integer> hamburgers = new ArrayList<Integer>();

	private static class Producer extends Thread {
		int i;

		@Override
		public void run() {
			while (true) {
				synchronized (hamburgers) {
					if (hamburgers.size() < 10) {
						hamburgers.add(i);
						System.out.println("生产汉堡" + i);
						i++;
						hamburgers.notifyAll();
					} else {
						try {
							hamburgers.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				try {
					Thread1.sleep(100);//模拟消耗的时间
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class Consumer extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronized (hamburgers) {
					if (hamburgers.size() > 0) {
						System.out.println("消费汉堡" + hamburgers.remove(0));
						hamburgers.notifyAll();
					} else {
						try {
							hamburgers.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				try {
					Thread1.sleep(100);//模拟消耗的时间
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

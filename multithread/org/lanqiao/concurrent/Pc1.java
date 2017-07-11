package org.lanqiao.concurrent;

import java.util.ArrayList;
import java.util.List;

public class Pc1 {
	private static List<Integer> list = new ArrayList<Integer>();
	private static Object lock = new Object();
	private static int i=0;
	public static void main(String[] args) {
		new Producer().start();
		new Consumer().start();
	}
	private static class Producer extends Thread{
		@Override
		public void run() {
				while(true){			
					synchronized(lock){
						if(list.size()<10){
							System.out.println("添加："+i);
							list.add(i++);
							lock.notifyAll();//让所有因为wait而阻塞的线程开始竞争
						}else{
							try {
								lock.wait();//释放锁，挂起，直到别的线程调用notify
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			
		}
	}
	private static class Consumer extends Thread{
		@Override
		public void run() {
				while(true){
					synchronized(lock){
					if (list.size() > 0) {
						Integer ii = list.remove(0);
						System.out.println("吃掉：" + ii);
						lock.notifyAll();
					} else {
						try {
							lock.wait();// 释放锁，挂起，直到别的线程调用notify
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			
		}
	}
}

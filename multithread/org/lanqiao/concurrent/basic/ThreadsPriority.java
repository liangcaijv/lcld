package org.lanqiao.concurrent.basic;

/**
 * 本例示范设定线程优先级及其效果
 * 可以看到StringTread在总体上得到了更优先的调度
 * @author zhengwei last modified:2012-4-30
 *
 */
public class ThreadsPriority {
	public static void main(String[] args) {
		NumTread t1 = new NumTread();
		StringTread t2 = new StringTread();
		
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		
		t1.start();
		t2.start();
	}
	
	static class NumTread extends Thread{
		int i=0;
		@Override
		public void run() {
			while(i<100){
				System.out.println("我在打印数字："+i++);
			}
		}
	}
	static class StringTread extends Thread{
		int i=0;
		@Override
		public void run() {
			while(i<100){
				System.out.println("我在打印字符串："+i++);
			}
		}
	}
}

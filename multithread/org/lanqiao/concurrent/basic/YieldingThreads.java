package org.lanqiao.concurrent.basic;

/**
 * 本例示范yield方法的效果
 * 可以看到StringTread得到了更优先的cpu时间
 * @author zhengwei last modified:2012-4-30
 *
 */
public class YieldingThreads {
	public static void main(String[] args) {
		NumTread t1 = new NumTread();
		StringTread t2 = new StringTread();
		t1.start();
		t2.start();
	}
	
	static class NumTread extends Thread{
		int i=0;
		@Override
		public void run() {
			while(i<100){
				if(i%10==0)
					Thread.yield();
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

package org.lanqiao.concurrent;

/**
 * 本例示范后台线程（守护线程）的效果
 * 主线程完成后，再无非守护线程运行，java程序退出
 * @author zhengwei last modified:2012-4-30
 *
 */
public class DaemonThreads {
	public static void main(String[] args) {
		DThread t = new DThread();
		t.start();
		
		System.out.println("让一切都结束吧");
	}
	private static class DThread extends Thread{
		public DThread() {
			setDaemon(true);//设置为守护线程
		}
		@Override
		public void run() {
			while(true){
				System.out.println("我是后台线程");
			}
		}
	}
}

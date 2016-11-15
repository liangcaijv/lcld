package chapter13;
public class DeadLockThread {
	
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();

	public static void main(String[] args) {
		new Thread(new ShareThread1()).start();
		new Thread(new ShareThread2()).start();
	}

	private static class ShareThread1 implements Runnable {
		public void run() {
			synchronized (lock1) {
				System.out.println("Thread1获取到lock1，休息一会"  );
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("尝试获取lock2锁");
				synchronized (lock2) {
					System.out.println("ShareThread1");
				}
			}
		}
	}

	private static class ShareThread2 implements Runnable {
		public void run() {
			synchronized (lock2) {
				System.out.println("Thread2获取到lock2休息一会");
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("尝试获取lock1锁");
				synchronized (lock1) {
					System.out.println("ShareThread2");
				}
			}
		}
	}
}

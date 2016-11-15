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
				System.out.println("Thread1��ȡ��lock1����Ϣһ��"  );
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("���Ի�ȡlock2��");
				synchronized (lock2) {
					System.out.println("ShareThread1");
				}
			}
		}
	}

	private static class ShareThread2 implements Runnable {
		public void run() {
			synchronized (lock2) {
				System.out.println("Thread2��ȡ��lock2��Ϣһ��");
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("���Ի�ȡlock1��");
				synchronized (lock1) {
					System.out.println("ShareThread2");
				}
			}
		}
	}
}

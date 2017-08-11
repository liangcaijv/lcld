package org.lanqiao.concurrent.sync;

/**
 * While it may seem obvious that NoVisibility will print 42, it is in fact
 * possible that it will print zero, or never terminate at all! Because it does
 * not use adequate synchronization, there is no guarantee that the values of
 * ready and number written by the main thread will be visible to the reader
 * thread. 
 * Even more strangely, NoVisibility could print zero because the write
 * to ready might be made visible to the reader thread before the write to
 * number, a phenomenon known as reordering.
 * 
 * @author zhengwei
 * 
 */
public class NoVisibility {
	private static boolean ready = false;
	private static int number;

	private static class ReaderThread extends Thread {
		public void run() {
			while (!ready)
				Thread.yield();
			System.out.println(number);
		}
	}

	public static void main(String[] args) throws Exception {
		new ReaderThread().start();
		Thread.sleep(100);
		number = 42;
		ready = true;
	}

}

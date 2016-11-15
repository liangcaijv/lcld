package chapter13;

class TicketSouce implements Runnable {

	// 总票数
	private int tickets = 100;

	// 具体任务执行方法
	public void run() {
		// 循环执行售票过程
		while (true) {
			// 模拟查票过程，网络上需要耗时，当前休眠1s秒中。
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//同步查票出票过程
			synchronized ("") {
				if (tickets > 0) {
					// 输出当前线程名称(票点名称)，以及已出票的座位号。并减少总票数
					System.out.println(Thread.currentThread().getName() + "号窗口卖出" + this.tickets-- + "号票");
				}
			}
		}
	}
}

public class TicketSale {
	public static void main(String args[]) {
		TicketSouce task = new TicketSouce();
		// 基于火车票创建三个窗口
		new Thread(task, "第一代售点").start();
		new Thread(task, "第二代售点").start();
		new Thread(task, "第三代售点").start();
	}
}
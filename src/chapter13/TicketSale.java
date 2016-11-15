package chapter13;

class TicketSouce implements Runnable {

	// ��Ʊ��
	private int tickets = 100;

	// ��������ִ�з���
	public void run() {
		// ѭ��ִ����Ʊ����
		while (true) {
			// ģ���Ʊ���̣���������Ҫ��ʱ����ǰ����1s���С�
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//ͬ����Ʊ��Ʊ����
			synchronized ("") {
				if (tickets > 0) {
					// �����ǰ�߳�����(Ʊ������)���Լ��ѳ�Ʊ����λ�š���������Ʊ��
					System.out.println(Thread.currentThread().getName() + "�Ŵ�������" + this.tickets-- + "��Ʊ");
				}
			}
		}
	}
}

public class TicketSale {
	public static void main(String args[]) {
		TicketSouce task = new TicketSouce();
		// ���ڻ�Ʊ������������
		new Thread(task, "��һ���۵�").start();
		new Thread(task, "�ڶ����۵�").start();
		new Thread(task, "�������۵�").start();
	}
}
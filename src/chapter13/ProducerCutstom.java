package chapter13;

import java.util.LinkedList;
import java.util.List;

public class ProducerCutstom {

	public static void main(String[] args) {
		Producer producer = new Producer();
		Cutstom cutstom = new Cutstom();

		new Thread(producer).start();

		new Thread(cutstom).start();

		new Thread(cutstom).start();

		new Thread(cutstom).start();
	}

}

/**
 * �̹߳�����Դ��
 */
class Resource {

	// �����󣬾�̬��Դ�����Ա���������
	static Object lock = new Object();

	// ������������̬��Դ�����Ա���������
	static List<String> container = new LinkedList<String>();
}

/**
 * ����������
 */
class Producer implements Runnable {

	/**
	 * �������ӹ���
	 */
	@Override
	public void run() {
		//�ظ������ӹ���
		while (true) {
			// ����Resource.lock����Ĳſ�ִ�����¹��̣���ִ���߳���Resource.lock������ͬ����
			synchronized (Resource.lock) {

				// ���������12�����ӣ������߳̾�Ҫ���ѳԻ��߳����԰���
				if (Resource.container.size() == 12) {

					System.out.println("һ�����ӳ�¯���Ի��������ðɣ�");
					Resource.lock.notifyAll();// ���������ڵȴ�Resource.lock��������߳�

				} else {
					System.out.println("��ʼ��������");
					// ģ���ʱ
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Resource.container.add("����");
				}
			}
		}
	}

}

class Cutstom implements Runnable {

	/**
	 * �԰��ӹ���
	 */
	@Override
	public void run() {
		
		//�ظ��԰��ӹ���
		while (true) {
	
			//�߳���Resource.lock��ͬ��
			synchronized (Resource.lock) {
				
				//������û�а����ˣ��Ի��Ǿ�Ҫ�ȴ�����	
				if (Resource.container.size() == 0) {
					
					System.out.println("���ӳԹ������ϰ��ϰ���");
					try {
						Resource.lock.wait();//�ͷ��Լ���������������ʼ�ȴ���notify
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else {
					System.out.println("�Ի��԰���");
					//ģ���ʱ
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//�Ƴ��Թ��İ���
					Resource.container.remove(0);
				}
			}
		}
	}

}
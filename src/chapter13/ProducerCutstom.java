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
 * 线程公用资源类
 */
class Resource {

	// 锁对象，静态资源，可以被公开访问
	static Object lock = new Object();

	// 包子容器，静态资源，可以被公开访问
	static List<String> container = new LinkedList<String>();
}

/**
 * 包子制作者
 */
class Producer implements Runnable {

	/**
	 * 制作包子过程
	 */
	@Override
	public void run() {
		//重复做包子过程
		while (true) {
			// 持有Resource.lock对象的才可执行以下过程，即执行线程在Resource.lock对象上同步。
			synchronized (Resource.lock) {

				// 当制作完成12个包子，制作线程就要唤醒吃货线程来吃包子
				if (Resource.container.size() == 12) {

					System.out.println("一笼包子出炉，吃货们来享用吧！");
					Resource.lock.notifyAll();// 唤醒所有在等待Resource.lock这把锁的线程

				} else {
					System.out.println("开始制作包子");
					// 模拟耗时
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Resource.container.add("包子");
				}
			}
		}
	}

}

class Cutstom implements Runnable {

	/**
	 * 吃包子过程
	 */
	@Override
	public void run() {
		
		//重复吃包子过程
		while (true) {
	
			//线程在Resource.lock上同步
			synchronized (Resource.lock) {
				
				//容器中没有包子了，吃货们就要等待包子	
				if (Resource.container.size() == 0) {
					
					System.out.println("包子吃光啦，老板上包子");
					try {
						Resource.lock.wait();//释放自己持有了锁，并开始等待被notify
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else {
					System.out.println("吃货吃包子");
					//模拟耗时
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//移除吃过的包子
					Resource.container.remove(0);
				}
			}
		}
	}

}
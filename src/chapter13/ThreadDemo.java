package chapter13;

public class ThreadDemo {
	public static void main(String[] args) {
		//��������Ĭ��һ���߳���ִ������

		//�����Ѷ����߳���ʵ��
		MyThread myThread = new MyThread();

		//����һ���߳�,start�����������̻߳�ִ��MyThread���е�run����
		myThread.start();
		
		//�������߳�ִ�д�ӡ
		System.out.println("�������߳���ִ��");
	}	
}

class MyThread extends Thread{
	
	@Override
	public void run() {
		System.out.println("MyThread ��ִ������");
	}
}


class RunnableTask implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("hello �һ���һ���߳�");
	}
	
}
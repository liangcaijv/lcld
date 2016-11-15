package chapter13;

public class ThreadDemo {
	public static void main(String[] args) {
		//主方法会默认一条线程来执行任务

		//创建已定义线程类实例
		MyThread myThread = new MyThread();

		//启动一条线程,start后新启动的线程会执行MyThread类中的run方法
		myThread.start();
		
		//主方法线程执行打印
		System.out.println("我是主线程在执行");
	}	
}

class MyThread extends Thread{
	
	@Override
	public void run() {
		System.out.println("MyThread 的执行任务");
	}
}


class RunnableTask implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("hello 我还是一条线程");
	}
	
}
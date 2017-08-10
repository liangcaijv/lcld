package org.lanqiao.concurrent.basic;

import java.util.Scanner;
/**
 * 本例示范如何正确地终止线程
 * @author zhengwei
 *
 */
public class EndingThreads {
	public static void main(String[] args) {
		CountThread t = new CountThread();
		t.start();
		
		Scanner scanner = new Scanner(System.in);
		while(true){
			String s = scanner.nextLine();
			if(s.equals("1")){
				t.stopIt();
				break;
			}
		}
	}
}

class CountThread extends Thread {
	private boolean flag = true;//设置标志变量
	private int i = 0;

	public void stopIt() {//公开改变标志变量的方法
		flag = false;
	}

	@Override
	public void run() {
		while (flag) {//每次运行判定标志变量
			System.out.println(i++);
		}
	}
}
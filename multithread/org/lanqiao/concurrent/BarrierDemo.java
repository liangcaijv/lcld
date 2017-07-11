package org.lanqiao.concurrent;

import java.util.concurrent.*;

public class BarrierDemo {
	final CyclicBarrier br;
	public BarrierDemo() {
		br = new CyclicBarrier(3, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("let us go!!!");
				
			}
		});
	}
	class Member implements Runnable{
		int index;
		public Member(int index) {
			this.index = index;
		}
		@Override
		public void run() {
			System.out.println("["+index+"]i am arrived...");
			try {
				br.await();//类似于报告说自己完成了，让等别的人
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
	void start(){
		for(int i=0;i<3;i++){
			new Thread(new Member(i)).start();
		}
	}
	public static void main(String[] args) {
		new BarrierDemo().start();
	}
}
class Solver {
	   final int N;
	   final float[][] data;
	   final CyclicBarrier barrier;
	   
	   class Worker implements Runnable {
	     int myRow;
	     boolean flag = true;
	     Worker(int row) { myRow = row; }
	     public void run() {
	       while (flag) {
//	         processRow(myRow);

	         try {
	           barrier.await(); 
	         } catch (InterruptedException ex) { 
	return; 
	         } catch (BrokenBarrierException ex) { 
	return; 
	         }
	       }
	     }
	   }

	   public Solver(float[][] matrix) {
	     data = matrix;
	     N = matrix.length;
	     barrier = new CyclicBarrier(N, 
	                                 new Runnable() {
	                                   public void run() { 
//	                                     mergeRows(...); 
	                                   }
	                                 });
	     for (int i = 0; i < N; ++i) 
	       new Thread(new Worker(i)).start();

//	     waitUntilDone();
	   }
	 }

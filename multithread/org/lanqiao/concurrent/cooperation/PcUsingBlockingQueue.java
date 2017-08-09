package org.lanqiao.concurrent.cooperation;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue 实现主要用于生产者-使用者队列,
 * 因为它的实现是线程安全的，同时put和take都会因为容量而自动阻塞
 * @author zhengwei
 *
 */
public class PcUsingBlockingQueue {
  private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
  private static int i = 0;

  public static void main(String[] args) {
    new Producer().start();
    new Consumer().start();
  }

  private static class Producer extends Thread {
    @Override
    public void run() {
      while (true) {
        try {
          System.out.println("添加：" + i);
          queue.put(i++);
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    }
  }

  private static class Consumer extends Thread {
    @Override
    public void run() {
      while (true) {
        try {
          System.out.println("消费：" + queue.take());
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    }
  }
}

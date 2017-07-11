package org.lanqiao.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
synchronized 的替代 Lock——ReentrantLock

 *
 */
public class Lock {
  private static int i = 0;
  private static ReentrantLock lock = new ReentrantLock();
  private static void inc() throws InterruptedException {
    lock.lock();
    //这里延迟1毫秒，使得结果明显
    TimeUnit.MILLISECONDS.sleep(1);
    i++;
    System.out.println(i);
    lock.unlock();
  }

  public static void main(String[] args) throws InterruptedException {
    //    启动1000个线程来增加i
    for (int i = 0; i < 1000; i++) {
      new Thread(() -> {
        try {
          inc();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }).start();
    }
    TimeUnit.SECONDS.sleep(2);
    System.out.println("----------"+i+"-------------");
//    实际运算结果每次可能都不一样,但都不是1000

  }
}


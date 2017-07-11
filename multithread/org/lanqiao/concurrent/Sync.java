package org.lanqiao.concurrent;

import java.util.concurrent.TimeUnit;

/**
synchronized 

同步块大家都比较熟悉，通过 synchronized 关键字来实现，所有加上synchronized 和 块语句，
在多线程访问的时候，同一时刻只能有一个线程能够用

synchronized 修饰的方法 或者 代码块。
 *
 */
public class Sync {
  private static volatile int i = 0;

  private synchronized static void inc() throws InterruptedException {
    //这里延迟1毫秒，使得结果明显
    TimeUnit.MILLISECONDS.sleep(1);
    i++;
    System.out.println(i);
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


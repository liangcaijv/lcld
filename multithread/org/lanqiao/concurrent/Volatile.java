package org.lanqiao.concurrent;

import java.util.concurrent.TimeUnit;

/**

volatile

用volatile修饰的变量，线程在每次使用变量的时候，都会读取变量修改后的最新值。
volatile很容易被误用，用来进行原子性操作。

对于volatile修饰的变量，jvm虚拟机只是保证从主内存加载到线程工作内存的值是最新的
 *
 */
public class Volatile {
  private static volatile int i = 0;

  private static void inc() throws InterruptedException {
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

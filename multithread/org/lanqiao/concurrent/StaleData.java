/**
 * http://www.cnblogs.com/aigongsi/archive/2012/04/01/2429166.html
 */
package org.lanqiao.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengwei
 *
 */
public class StaleData {
  private static int i = 0;

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

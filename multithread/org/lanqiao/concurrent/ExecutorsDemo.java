package org.lanqiao.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executor和task优先于线程
 * 
 * @author zhengwei
 *
 */
public class ExecutorsDemo {
  public static void main(String[] args) {
    ScheduledExecutorService executorService = Executors
        .newScheduledThreadPool(5);
    executorService.scheduleAtFixedRate(() -> {
      System.out.println("正在执行任务");
    }, 3, 1, TimeUnit.SECONDS);
    executorService.shutdown();
  }

  public class ExecutorDemo {
    public  void test() {
      //创建一个数目最大为Integer.MAX的池，新任务执行的时候，将尽量重用已有的可用线程，否则新建线程
      ExecutorService executor1 = Executors.newCachedThreadPool();
      //创建数目固定为100的线程池，新任务执行的时候，如果100个线程都在活动，新任务将会被放在等待队列中
      ExecutorService executor2 = Executors.newFixedThreadPool(100);
      //只会有一个活动线程，但是等待队列无边界，它可以保证执行的任务是按顺序的
      ExecutorService executor3 = Executors.newSingleThreadExecutor();
      //可调度的线程池，执行任务的时候可设定延迟及重复周期等参数
      ScheduledExecutorService executor4 = Executors
          .newScheduledThreadPool(1000);
      executor4.schedule(new Runnable() {

        @Override
        public void run() {
          System.out.println("hello");
        }
      }, 1, TimeUnit.SECONDS);

      executor4.scheduleAtFixedRate(new Runnable() {

        @Override
        public void run() {
          System.out.println("每三秒执行一次……");

        }
      }, 5, 3, TimeUnit.SECONDS);

      executor4 = Executors.newSingleThreadScheduledExecutor();
    }
  }

}

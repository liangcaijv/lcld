package org.lanqiao.concurrent.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <Strong>Executor和task优先于原始线程创建</Strong><br/>
 * Executors是创建执行器的工具类，通常使用以下几个方法来返回一个ExecutorService（Executor的子类）<br/>
 * ScheduledExecutorService——newScheduledThreadPool<br/>
 * ExecutorService——newCachedThreadPool<br/>
 * ExecutorService——newFixedThreadPool<br/>
 * ExecutorService——newSingleThreadExecutor<br/>
 * 从Executors的角度看，生产可调度执行器和普通执行器，普通执行器又分为三种：<br/>
 * 缓存线程池，固定数量线程池，单线程（池中只有一个线程）执行器<br/>
 * 这三种普通执行器都是ThreadPoolExecuror的实例<br/>
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
    new ExecutorsDemo().new ExecutorDemo().test();
  }

  public class ExecutorDemo {
    public void test() {
      //创建一个数目最大为Integer.MAX的池，新任务执行的时候，将尽量重用已有的可用线程，否则新建线程
      ExecutorService executor1 = Executors.newCachedThreadPool();
      //创建数目固定为100的线程池，新任务执行的时候，如果100个线程都在活动，新任务将会被放在等待队列中
      ExecutorService executor2 = Executors.newFixedThreadPool(100);
      //只会有一个活动线程，但是等待队列无边界，它可以保证执行的任务是按顺序的
      ExecutorService executor3 = Executors.newSingleThreadExecutor();
      Future<String> future = executor3.submit(new Callable<String>() {

        @Override
        public String call() throws Exception {
          TimeUnit.SECONDS.sleep(10);
          return "hi";
        }

      });
      try {
        String futureResult = future.get();
        System.out.println(futureResult);
      } catch (InterruptedException | ExecutionException e) {
        future.cancel(true);
      }
      //可调度的线程池，执行任务的时候可设定延迟及重复周期等参数
      ScheduledExecutorService executor4 = Executors
          .newScheduledThreadPool(1000);
      executor4.schedule(() -> {
        System.out.println("hello");
      }, 1, TimeUnit.SECONDS);

      executor4.scheduleAtFixedRate(() -> {
        System.out.println("每三秒执行一次……");
      }, 5, 3, TimeUnit.SECONDS);

    }
  }

}

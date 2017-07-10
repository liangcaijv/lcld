package org.lanqiao.multithread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 同步器
 * BlockQueue就是一个同步器，可以解决生产者消费者问题
 * 门栓：latch（CountDownLatch），多个线程各自执行，但是等到某个事件发生（归零），取消阻塞——想向终点集合
 * 未来任务：FutureTask，假如一个任务很耗时，我们可以定义一个带有返回值的任务FutureTask，在控制线程中调用其get方法将被阻塞直到任务完成
 * Callable是一个带有结果的Runnable
 * Semaphore：信号量，限定访问资源的线程，只有许可数>0时，竞争到许可的线程才会解除阻塞，可以形成互斥
 * CycliBarrier（循环栅栏）：线程在栅栏前排队，每个都调用await方法，所有的线程都调用过await之后，阻塞状态全部接触——想向起跑
 * 
 * @author zhengwei lastmodified 2017年7月10日
 *
 */
public class SynchronizerDemo {

  static class CountDownLatchDemo {
    // 闭锁，包含一个计数器，初始化为正数，用来表示事件的数量。
    //    其countDown方法被调用时，代表一个事件已经发生了
    //    而await方法等待计数器归零，此时，所有需要等待的事件都已完成
    //    假设我们有一个非常大的文件需要处理，我们可以将它分割成若干部分，单个线程处理其中一个部分，我们要等到所有线程都处理完再来对结果进行合并
    //    此时就可以使用CountDownLatch来作为同步器
    CountDownLatch countDownLatch = new CountDownLatch(10);

    void test() {
      //      控制线程
      new Thread(() -> {
        try {
          countDownLatch.await();
          System.out.println("大家都干完了，回家休息吧");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }).start();

      //      工作线程
      for (int i = 0; i < 10; i++) {
        new Thread(() -> {
          System.out.println("我干了一件事");
          countDownLatch.countDown();
        }).start();
      }

    }

  }

  static class FutureTaskDemo {
    void test() throws InterruptedException, ExecutionException {
      FutureTask<Object> task = new FutureTask<>(new Callable<Object>() {

        @Override
        public Object call() throws Exception {
          TimeUnit.SECONDS.sleep(1);
          return new Object();//这里模拟一个耗时比较长的加载对象的操作
        }
      });

      new Thread(task).start();
      Object obj = task.get();//get方法将被阻塞，直到task的callable返回对象

      System.out.println(obj);
    }
  }

  static class SemaphoreDemo {
    void test() {
      Semaphore semp = new Semaphore(5);
      // 模拟20个客户端访问
      for (int index = 0; index < 20; index++) {
        final int NO = index;
        new Thread(() -> {
          try {
            // 获取许可
            semp.acquire();
            System.out.println("Accessing: " + NO);
            Thread.sleep((long) (Math.random() * 10000));
            // 访问完后，释放
            semp.release();
            System.out.println("-----------------" + semp.availablePermits());
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }).start();
      }
    }
  }

  static class CyclicBarrierDemo {

    public void test()  {
      //如果将参数改为4，但是下面只加入了3个选手，这永远等待下去  
      //Waits until all parties have invoked await on this barrier.   
      CyclicBarrier barrier = new CyclicBarrier(3);

      ExecutorService executor = Executors.newFixedThreadPool(3); // 线程池 
      executor.submit(new Thread(new Runner(barrier, "1号选手")));
      executor.submit(new Thread(new Runner(barrier, "2号选手")));
      executor.submit(new Thread(new Runner(barrier, "3号选手")));

      executor.shutdown();
    }

    class Runner implements Runnable {
      // 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)  
      private CyclicBarrier barrier;

      private String        name;

      public Runner(CyclicBarrier barrier, String name) {
        super();
        this.barrier = barrier;
        this.name = name;
      }

      @Override
      public void run() {
        try {
          Thread.sleep(1000 * (new Random()).nextInt(8));
          System.out.println(name + " 准备好了...");
          // barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。  
          barrier.await();
          System.out.println(name + " 到达...");
        } catch (InterruptedException | BrokenBarrierException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    //    new CountDownLatchDemo().test();
//    new SemaphoreDemo().test();
    new CyclicBarrierDemo().test();
  }
}

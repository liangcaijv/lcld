package org.lanqiao.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者问题
 * 
 * @author zhengwei
 *
 */
public class ProducerAndConsumer {
  public static void main(String[] args) {
    //    new Test1().test();
//    new Test2().test();
    new Test3().test();
  }

  /**
   * 生产者和消费者，wait()和notify()的实现
   */
  static class Test1 {
    private static Integer       count = 0;
    private static final Integer FULL  = 10;
    private static String        LOCK  = "lock";

    public void test() {
      // 10个线程
      ExecutorService executor = Executors.newFixedThreadPool(10);
      for (int i = 0; i < 5; i++) {
        executor.submit(this.new Producer());
        executor.submit(this.new Consumer());
      }
      executor.shutdown();
    }

    /**
     * 生产者
     * 
     * @author zhengwei
     *
     */
    class Producer implements Runnable {
      @Override
      public void run() {
        try {
          synchronized (LOCK) { //获得锁
            if (count == FULL) { // 如果数量已足够
              LOCK.wait(); // 阻塞
            } else { // 数量不足够，继续生产
              for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1); //  模拟生产时间
                count++;
                System.out.println(Thread.currentThread().getName()
                    + "生产者生产，目前总共有" + count);
                LOCK.notifyAll(); // 通知其他线程
              }
            }
          }
        } catch (InterruptedException e1) {
          Thread.currentThread().interrupt();
        }
      }
    }

    class Consumer implements Runnable {
      @Override
      public void run() {
        try {
          synchronized (LOCK) {
            if (count == 0) {
              LOCK.wait();
            } else {
              for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1); //  模拟消费时间
                count--;
                System.out.println(Thread.currentThread().getName()
                    + "消费者消费，目前总共有" + count);
                LOCK.notifyAll();
              }
            }
          }
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }

  /**
   * 生产者和消费者，ReentrantLock的实现
   */
  static class Test2 {
    private static Integer       count    = 0;
    private static final Integer FULL     = 10;
    //创建一个锁对象
    private ReentrantLock        lock     = new ReentrantLock();
    private Condition            notFull  = lock.newCondition();
    private Condition            notEmpty = lock.newCondition();

    public void test() {
      // 10个线程
      ExecutorService executor = Executors.newFixedThreadPool(10);
      Producer producerTask = this.new Producer();
      executor.submit(producerTask);
      for (int i = 0; i < 9; i++) {
        executor.submit(this.new Consumer());
      }
    }

    /**
     * 生产者
     * 
     * @author zhengwei
     *
     */
    class Producer implements Runnable {
      private boolean goon = true;

      @Override
      public void run() {
        while (goon) {
          try {
            lock.lock();
            while (count < FULL) {
              // 数量不足够，继续生产
              TimeUnit.SECONDS.sleep(1); //  模拟生产时间
              count++;
              System.out.println(Thread.currentThread().getName()
                  + "生产者生产，目前总共有" + count);
            }
            notEmpty.signal();
            lock.unlock();
          } catch (InterruptedException e1) {
            Thread.currentThread().interrupt();
          }
        }
      }

      void stop() {
        goon = false;
      }
    }

    class Consumer implements Runnable {
      @Override
      public void run() {
        lock.lock();
        if (count == 0) {
          try {
            notEmpty.await();
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        for (int i = 0; count > 0 && i < 2; i++) {
          count--;
          System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有"
              + count);
        }
        lock.unlock();
      }
    }
  }

  /**
   * 生产者和消费者，Semaphore的实现
   */
  static class Test3 {
    private static Integer       count             = 0;
    private static final Integer FULL              = 10;
    private Semaphore            waitingsSemaphore = new Semaphore(10);
    private Semaphore            mutexseSemaphore  = new Semaphore(1);

    public void test() {
      // 10个线程
      ExecutorService executor = Executors.newFixedThreadPool(10);
      Producer producerTask = this.new Producer();
      executor.submit(producerTask);
      for (int i = 0; i < 50; i++) {
        executor.submit(this.new Consumer());
      }
    }

    /**
     * 生产者
     * 
     * @author zhengwei
     *
     */
    class Producer implements Runnable {
      private boolean goon = true;

      @Override
      public void run() {
        while (goon) {
          try {
            mutexseSemaphore.acquire();
            while (count < FULL) {
              // 数量不足够，继续生产
              TimeUnit.SECONDS.sleep(1); //  模拟生产时间
              count++;
              System.out.println(Thread.currentThread().getName()
                  + "生产者生产，目前总共有" + count);
            }
            mutexseSemaphore.release();
          } catch (InterruptedException e1) {
            Thread.currentThread().interrupt();
          }
        }
      }

      void stop() {
        goon = false;
      }
    }

    class Consumer implements Runnable {
      @Override
      public void run() {
        try {
          waitingsSemaphore.acquire();
          mutexseSemaphore.acquire();
          if (count == 0) {
            mutexseSemaphore.release();
          } else {
            for (int i = 0; count > 0 && i < 2; i++) {
              count--;
              System.out.println(Thread.currentThread().getName()
                  + "消费者消费，目前总共有" + count);
            }
            waitingsSemaphore.release();
            mutexseSemaphore.release();
          }
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }
}

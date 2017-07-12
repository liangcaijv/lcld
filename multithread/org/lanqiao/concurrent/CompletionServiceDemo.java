package org.lanqiao.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CompletionServiceDemo {
  private static Integer i = 0;
  private static Semaphore mutex = new Semaphore(1);
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
        executorService);
    Callable<Integer> task = new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        mutex.acquire();
        TimeUnit.SECONDS.sleep(1);
        i++;
        mutex.release();
        return i;
      }
    };
    for (int i = 0; i < 10; i++) {
      completionService.submit(task);
    }
    for (int i = 0; i < 10; i++) {
      try {
        Future<Integer> future = completionService.take();
        System.out.println(future.get());
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      } catch (ExecutionException e) {
        throw launderThrowable(e.getCause());
      }
    }
    executorService.shutdown();
  }

  /**
   * If the Throwable is an Error, throw it; if it is a RuntimeException return
   * it, otherwise throw IllegalStateException
   */
  public static RuntimeException launderThrowable(Throwable t) {
    if (t instanceof RuntimeException)
      return (RuntimeException) t;
    else if (t instanceof Error)
      throw (Error) t;
    else
      throw new IllegalStateException("Not unchecked", t);
  }
}

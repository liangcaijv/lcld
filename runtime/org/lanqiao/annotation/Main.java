package org.lanqiao.annotation;

public class Main {
  public static void main(String[] args) {
    IWorker worker;
    //    worker= WorkerFactory.getWorkerByJdkDynamicProxy(Worker.class);
    worker = WorkerFactory.getWorkerUsingCglibProxy(Worker.class);
    worker.logic1();
    worker.logic2();
  }
}

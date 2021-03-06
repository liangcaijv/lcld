package org.lanqiao.concurrent.basic;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicXxx {
  private AtomicLong number = new AtomicLong(10000L);
//  多线程访问下，自增操作是原子的
  void inc(){
    number.incrementAndGet();
  }
  long get(){
    return number.longValue();
  }
  public static void main(String[] args) {
    AtomicXxx obj = new AtomicXxx();
    obj.inc();
    System.out.println(obj.get());
  }
}

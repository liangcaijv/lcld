package org.lanqiao.annotation;

import java.util.concurrent.TimeUnit;

/**
 * 基于cglib的代理，如果要解析注解，那么注解一定要标注在类本身上
 * @author zhengwei lastmodified 2017年7月12日
 *
 */
public class Worker implements IWorker {
  @Override
  @ShowDuration
  public void logic1() {
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public void logic2() {
  }
}

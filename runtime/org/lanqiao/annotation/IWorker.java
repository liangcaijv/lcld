package org.lanqiao.annotation;

/**
 * 接口
 * 基于接口的代理如果要解析注解，注解一定要放在接口上
 * @author zhengwei lastmodified 2017年7月12日
 *
 */
public interface IWorker {
  @ShowDuration
  void logic1();
  @ShowDuration(false)
  void logic2();

}
package org.lanqiao.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Throwables;
import com.google.common.reflect.Reflection;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class WorkerFactory {
  /**
   * 基于jdk动态代理模式获取一个代理对象，在反射调用方法时，判断方法上是否有注解
   * 在代理对象上增强原有功能！！！
   * @param claz
   * @return
   */
  public static IWorker getWorkerByJdkDynamicProxy(Class<? extends IWorker> claz) {
    //    ------------一定是这种模式把目标对象传递给handler----------
    class Handler implements InvocationHandler {
      private IWorker target;

      public Handler(IWorker target) {
        super();
        this.target = target;
      }

      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ShowDuration ann = method.getAnnotation(ShowDuration.class);
        Object result;
        if (ann != null && ann.value()) {
          long startTime = System.nanoTime();
          // ... the code being measured ...
          result = method.invoke(target, args);
          long estimatedTime = System.nanoTime() - startTime;
          System.out.println(method.toGenericString() + "-持续：" + TimeUnit.NANOSECONDS.toSeconds(estimatedTime) + "秒");
        } else {
          result = method.invoke(target, args);
        }
        return result;
      }
    }
    try {
      final IWorker target = claz.newInstance();
      //      return (IWorker) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
      //          new Handler(target)); // 原生jdk代理对象的生成
      //      --------------guava----------------------------
      return Reflection.newProxy(IWorker.class, new Handler(target));//guava简化了API
    } catch (InstantiationException | IllegalAccessException e) {
      Throwables.throwIfUnchecked(e);
    }
    return null;
  }

  /**
   * cglib形式的代理对象
   * @param claz
   * @return
   */
  public static IWorker getWorkerUsingCglibProxy(Class<? extends IWorker> claz) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(claz);
    enhancer.setCallback(new MethodInterceptor() {

      //      ----obj是新对象，即代理对象---
      //      ----proxy是旧对象，要调原方法：proxy.invokeSuper(obj,args)
      @Override
      public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        ShowDuration ann = method.getAnnotation(ShowDuration.class);
        Object result;
        if (ann != null && ann.value()) {
          long startTime = System.nanoTime();
          // ... the code being measured ...
          result = proxy.invokeSuper(obj, args);
          long estimatedTime = System.nanoTime() - startTime;
          System.out.println(method.toGenericString() + "-持续：" + TimeUnit.NANOSECONDS.toSeconds(estimatedTime) + "秒");
        } else {
          result = proxy.invokeSuper(obj, args);
        }
        return result;
      }

    });
    return (IWorker) enhancer.create();
  }
}

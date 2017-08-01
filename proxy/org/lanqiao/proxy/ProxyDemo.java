package org.lanqiao.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * JDK动态代理的基本示例 注意动态代理必须使用接口，因为是基于接口的代理
 * 还有一种是cglib之类的，直接修改字节码的代理方式，不需要目标对象所属类实现任何接口
 * 
 * @author zhengwei
 *
 */
public class ProxyDemo {
  public static void main(String[] args) {
    // 创建一个原生对象
    Target target = new Target();
    // 实际处理代理行为
    InvocationHandler handler = new DemoHandler(target);
    ClassLoader loader = TargetInterface.class.getClassLoader();
    TargetInterface proxyObject = (TargetInterface) Proxy.newProxyInstance(
        loader, new Class[] { TargetInterface.class }, handler);
    proxyObject.f();
  }
}

interface TargetInterface {
  void f();
}

class Target implements TargetInterface {
  @Override
  public void f() {
    System.out.println("我是目标对象的f方法");
  }
}

class DemoHandler implements InvocationHandler {
  Target target;

  public DemoHandler(Target target) {
    this.target = target;
  }

  // 调用行为写在这里
  @Override
  public Object invoke(Object proxy, Method method, Object[] args)
      throws Throwable {
    // 增加前置的代理行为
    System.out.println("目标对象正在调用" + method + ",我是前置通知");
    //    调用原始对象的行为
    //method.invoke(target, args);//  这句话相当于target.method(args);
    //    增加后置的代理行为
    System.out.println("该方法的参数是："
        + (null == args ? "没有参数" : Arrays.asList(args).toString()) + ",我是后置通知");
    return null;
  }
}

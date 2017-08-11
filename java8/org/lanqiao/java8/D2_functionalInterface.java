/**
 *
 */
package org.lanqiao.java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式接口——功能性接口：只描述一个行为，没有状态
 * 语法：该接口只有一个逻辑上的抽象方法，可以有默认实现，可以有Object的public方法，别的不能有
 * 函数式接口的实例可以用lamda表达式来创建
 * Runnable是函数式接口
 * JDK 1.8之前已有的函数式接口:
 java.lang.Runnable
 java.util.concurrent.Callable
 java.security.PrivilegedAction
 java.util.Comparator
 java.io.FileFilter
 java.nio.file.PathMatcher
 java.lang.reflect.InvocationHandler
 java.beans.PropertyChangeListener
 java.awt.event.ActionListener
 javax.swing.event.ChangeListener
 * 新增的函数式接口都在java.util.function包下
 * {@link Consumer} 消费者
 * {@link Function} 功能
 * {@link Predicate} 谓词
 * {@link Supplier} 生产者
 * 它们都被注解为{@link FunctionalInterface}
 * @author zhengwei lastmodified 2017年3月29日
 *
 */
public class D2_functionalInterface {
  public static void main(String[] args) {
    D2_functionalInterface app = new D2_functionalInterface();
    app.f1();
    app.f2();
    app.f4();
  }

  /* Consumer 消费者 */
  void f1() {
    useConsumer( something -> {
      System.out.println( something );
    }, "real something" );
  }

  void f2() {
    int length = useFunction( s -> {
      return s.length();
    }, "zhengwei" );

    System.out.println( length );
  }

  void f3() {
    boolean result = usePredicate( s -> {
      return s >= 60;
    }, 70 );
  }

  void f4() {
    System.out.println( useSupplier( () -> {
      return "something";
    } ) );
  }

  private <T> void useConsumer(Consumer<T> action, T something) {
    action.accept( something );
  }

  private Integer useFunction(Function<String, Integer> mapper, String something) {
    return mapper.apply( something );
  }

  private <T> boolean usePredicate(Predicate<T> filter, T something) {
    return filter.test( something );
  }

  private String useSupplier(Supplier<String> factory) {
    return factory.get();
  }
}

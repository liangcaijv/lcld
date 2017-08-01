package org.lanqiao.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射的第二个主要作用是动态实例化
 * @author zhengwei
 *
 */
public class _02_DynamicInstance {
  public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
    Integer obj = createInstance("java.lang.Integer",new Class[]{int.class},10);
    System.out.println(obj.intValue());
  }
  
  // 我们在编写代码的时候，并不知道类名，我们只知道客户端会传递一个类名过来，现在要创建这个类的实例
  /**
   * 只能用于调用无参构造器
   * @param <T>
   * @param className
   * @return
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  static <T> T createInstance(String className) 
      throws ClassNotFoundException, InstantiationException, IllegalAccessException{
    Class<T> claz = (Class<T>) Class.forName(className);
    return claz.newInstance();
  }
  
  static <T> T createInstance(String className,Class<?>[] parameterTypes,Object... args) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    Class<T> claz = (Class<T>) Class.forName(className);
    Constructor<T> constructor = claz.getConstructor(parameterTypes);
    return constructor.newInstance(args);
  }
  
  
}

package org.lanqiao.guava.reflect;

import java.lang.reflect.Method;

import org.lanqiao.reflect.User;

import com.google.common.reflect.Invokable;

public class InvokableDemo {
  public static void main(String[] args) throws Exception {
    Class<User> claz = User.class;
    Method m = claz.getDeclaredMethod("setUsername", String.class);
    User user = claz.newInstance(); // 实例
    
    Invokable invokable = Invokable.from(m);
    invokable.invoke(user, "zhangsan");
    
    
    System.out.println(user.getUsername());
  }
}

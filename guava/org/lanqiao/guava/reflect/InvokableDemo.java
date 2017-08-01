package org.lanqiao.guava.reflect;

import java.lang.reflect.Method;

import org.lanqiao.reflect.User;

import com.google.common.reflect.TypeToken;

public class InvokableDemo {
  @SuppressWarnings({ "serial"})
  public static void main(String[] args) throws Exception {
    Class<User> claz = User.class;
    Method m = claz.getDeclaredMethod("setUsername", String.class);
    User user = claz.newInstance(); // 实例

    new TypeToken<User>() {
    }.method(m).invoke(user, "zhangsan");

    System.out.println(user.getUsername());
  }
}

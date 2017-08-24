package org.lanqiao.classLoader;

import javafx.application.Application;

public class ClassLoaderTest1 {
  public static void main(String[] args) {
    //主类加载器
    System.out.println(ClassLoaderTest1.class.getClassLoader());
    //主类加载器的父类加载器
    System.out.println(ClassLoaderTest1.class.getClassLoader().getParent());
    //主类加载器的父类加载器的父加载器
    System.out.println(ClassLoaderTest1.class.getClassLoader().getParent().getParent());

    System.out.println(String.class.getClass().getClassLoader());
  }
}

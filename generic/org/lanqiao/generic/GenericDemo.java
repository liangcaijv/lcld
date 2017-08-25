package org.lanqiao.generic;

import java.util.ArrayList;
import java.util.List;

class A<T> {
}
class B<K,V>{}

class C<T> extends A<T>{}
public class GenericDemo {

  /*void apply1(List<Object> list){
    boolean b = list instanceof List<Object>; // error
    b = list instanceof  List<?>;// right
  }*/
  void apply(List<?> list) {
    // 数组只能存储同一种类型，运行时泛型被擦除，编译器无法保证A对象的类型参数是String
    //    A<String>[] lsa = new A<String>[10]; // error
    A<?>[] gArr = new A<?>[10]; // right，但是一般不这样用，取出来之后比较麻烦
//    list.add("");  // error
  }

  <T> T  m1() throws IllegalAccessException, InstantiationException {
    Class<T> claz = null;
    return claz.newInstance();
  }

  void test() {
    apply(new ArrayList<String>());
    /*new A<?>();
    new A<T>();*/
  }

  void testM() throws InstantiationException, IllegalAccessException {
    B<?,String> b = new B<String,String>();
//    B<Object,String> b1 = new B<String,String>();
    m1();
  }
}

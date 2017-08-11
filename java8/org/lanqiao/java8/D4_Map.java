package org.lanqiao.java8;

import java.util.HashMap;
import java.util.Map;

public class D4_Map {

  public static void main(String[] args) {
    Map<Integer, String> map = new HashMap<>();
    for (int i = 0; i < 10; i++) {
      map.putIfAbsent( i, "val" + i );
    }

    map.forEach( (k, v) -> System.out.println( k + ":" + v ) );
    compute( map );

    merge( map );
  }

  private static void merge(Map<Integer, String> map) {
    // 1.检查key值存在，原value和第二个参数之时的新值传入第三个参数指示的双值函数，函数运算出新值重新映射
    //2.检查key值不存在，则将第一个和第二个参数作为k-v插入map
    map.merge( 9, "newValue", String::concat );
    map.forEach( (k, v) -> System.out.println( k + ":" + v ) );
  }

  private static void compute(Map<Integer, String> map) {
    // 重新映射，key如果不存在，则新增k,v
    map.compute( 10, (k, v) -> v + "2" );
    //先检查key须存在，存在则重新计算值并重新映射
    map.computeIfPresent( 11, (k, v) -> k + "" );
    // 先检查key须不存在，计算出一个value并将k-v存入
    map.computeIfAbsent( 12, (k) -> k + "" );
    map.forEach( (k, v) -> System.out.println( k + ":" + v ) );
  }
}

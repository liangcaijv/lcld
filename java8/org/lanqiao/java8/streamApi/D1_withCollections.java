package org.lanqiao.java8.streamApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在 Java 8 中, 集合接口有两个方法来生成流：<br>
 * stream() − 为集合创建串行流。<br>
 * parallelStream() − 为集合创建并行流。<br>
 * @author zhengwei lastmodified 2017年3月29日
 *
 */
public class D1_withCollections {
  public static void main(String[] args) {
    //sortAndForeach();
    //filterAndCollect();
    //match();
    reduce();
  }

  /**
   * 排序和外部迭代
   */
  private static void sortAndForeach() {
    Arrays.asList( "a", "b", "d", "c" ).stream().sorted( (a, b) -> {
      return b.compareTo( a );
    } ).forEach( e -> {
      System.out.println( e );
    } );
  }

  private static void filterAndCollect() {
    List<Integer> list = Arrays.asList( 1, 2, 3, 4, -1, -2, -3 );
    List<Integer> newList = list.stream().filter( (e) ->
        e > 0
    ).sorted( (a, b) ->
        a < b ? 1 : (a > b ? -1 : 0)
    ).collect( Collectors.toList() );
    newList.stream().forEach( System.out::println );
  }

  private static void match() {
    List<Integer> list = Arrays.asList( 1, 2, 3, 4, -1, -2, -3 );
    boolean allPositive = list.stream().allMatch( (e) ->
        e > 0
    );
    System.out.println( allPositive );
  }

  private static void reduce() {
    List<Integer> list = Arrays.asList( 1, 2, 3, 4, -1, -2, -3 );
    //用归约求和
    int sum = list.stream().reduce( (a, b) -> a + b ).get();
    System.out.println( sum );
  }
}

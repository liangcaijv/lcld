
package org.lanqiao.java8.streamApi;

import java.util.Arrays;

/**
 * 在 Java 8 中, 集合接口有两个方法来生成流：<br>
 * stream() − 为集合创建串行流。<br>
 * parallelStream() − 为集合创建并行流。<br>
 * @author zhengwei lastmodified 2017年3月29日
 *
 */
public class D1_withCollections {
	public static void main(String[] args) {
		sortAndForeach();
		
	}
	/**
	 * 排序和外部迭代
	 */
	private static void sortAndForeach() {
		Arrays.asList("a", "b", "d", "c").stream().sorted((a, b) -> {
			return b.compareTo(a);
		}).forEach(e -> {
			System.out.println(e);
		});
	}
}

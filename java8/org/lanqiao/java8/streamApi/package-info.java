
/**
 * 流API<br/>
Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。<br/>
Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。<br/>
Stream API可以极大提供Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。<br/>
这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。<br/>
元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。<br/>
+--------------------+       +------+   +------+   +---+   +-------+<br/>
| stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|<br/>
+--------------------+       +------+   +------+   +---+   +-------+<br/>
 * @author zhengwei lastmodified 2017年3月29日
 *
 */
package org.lanqiao.java8.streamApi;
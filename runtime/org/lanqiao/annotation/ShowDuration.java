package org.lanqiao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 注解的意义在于解析它的工具，怎么解析是解析器的事
 * 不过仍然要把名字和意义等同起来
 * @author zhengwei lastmodified 2017年7月12日
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ShowDuration {
  boolean value() default true;
}

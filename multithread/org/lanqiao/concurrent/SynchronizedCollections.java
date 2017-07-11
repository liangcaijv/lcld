package org.lanqiao.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * 同步集合
 * @author zhengwei lastmodified 2017年7月10日
 *
 */
public class SynchronizedCollections {
  public static void main(String[] args) {
    // 同步map
    Map<String, String> map = Collections.synchronizedMap(new HashMap<String,String>());
  }
}

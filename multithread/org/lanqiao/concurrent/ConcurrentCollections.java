package org.lanqiao.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//线程安全类——它的对象暴露在多个线程之下，不会出现数据不一致性
@SuppressWarnings("unchecked")
public class ConcurrentCollections {
  private List list = new ArrayList();

  public List getList() {
    return Collections.synchronizedList(list);
  }

  private Map map = new ConcurrentHashMap();

  class UnSafe {
    List list = new ArrayList();

    public List getList() {
      return list;
    }

    public void add(Object obj) {
      if (list.size() < 100)
        list.add(obj);
    }
  }
}

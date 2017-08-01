package org.lanqiao.guava.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.TypeToken;

public class TypeTokenDemo {
  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();
    ParameterizedType type = (ParameterizedType) new TypeToken<List<String>>() {
    }.getType();
    System.out.println(type.getActualTypeArguments()[0]);

    System.out.println(new DAO<String>(){}.getEntityType());
  }
}

class DAO<T> {
  private Type entityType;

  public DAO() {
    Type superclass = getClass().getGenericSuperclass();
    if (superclass instanceof ParameterizedType) {
      this.entityType = ((ParameterizedType) superclass)
          .getActualTypeArguments()[0];
    }
  }

  Type getEntityType() {
    return this.entityType;
  }
}
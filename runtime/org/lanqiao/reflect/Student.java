package org.lanqiao.reflect;

public class Student {
  String name;
  int age;
  String other;

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }
public int getAge() {
  return age;
}
  @Override
  public String toString() {
    return "Student [name=" + name + ", age=" + age + ", other=" + other + "]";
  }



}
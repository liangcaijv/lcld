package org.lanqiao.commons;

public class Randoms {
  private Randoms() {
  }

  public static String getString(int number) {

    String result = "";

    int i = 0;
    while (i < number) {
      char c = (char) (Math.random() * (0xffff + 1));
      if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
        result += c;
        i++;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println( getString( 10 ) );
  }
}

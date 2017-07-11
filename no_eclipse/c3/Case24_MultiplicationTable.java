import java.util.Scanner;

public class Case24_MultiplicationTable{
  public static void main(String[] args) {
    // 外层控制行
    // 内层控制列

    for(int i = 1;i<=9;i++){
      // 一趟：打印99表的一行，边界1~i（行号）
      for(int j = 1;j<=i;j++){
        System.out.printf("%d * %d = %d\t",i,j,i*j);
      }
      System.out.println();
    }
  }
}
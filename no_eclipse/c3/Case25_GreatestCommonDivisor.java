import java.util.Scanner;

/**
* 最大公约数 greatest common divisor
*/
public class Case25_GreatestCommonDivisor{
  public static void main(String[] args) {
    System.out.println("请输入两个正整数：");
    Scanner in = new Scanner(System.in);
    int x = in.nextInt();
    int y = in.nextInt();

    if(x>y){
      x = x^y;
      y = x^y;
      x = x^y;
    }else if(x==y){
      // 最大公约数就是x
      System.out.printf("%d和%d的最大公约数是：%d\n",x,y,x);
      System.exit(0);
    }

    // int gcd = 1;
    // if(y%x==0){
    //   System.out.printf("%d和%d的最大公约数是：%d\n",x,y,x);
    //   System.exit(0);
    // }
    // for(int i = x/2; i>0;i--){
    //   if(x%i==0&&y%i==0){
    //     gcd = i;
    //     break;
    //   }
    // }
    while(y%x != 0){
     int tmp = x;
     x = y%x;
     y = tmp;
    }
    System.out.printf("%d和%d的最大公约数是：%d\n",x,y,x);

  }
}
import java.util.Scanner;
//闰年
public class Case9_LeapYear{
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("请输入一个大于1582的年份：" );
    int year  = in.nextInt();
    if (year > 1582) {
        if((year%4==0&&year%100!=0) || year%400==0){
            System.out.println("这是一个闰年");       
        }else{
            System.out.println("这不是一个闰年");    
        }
    }else{
        System.out.println("咋不听话呢？说了1582前还没有公历这回事！");   
    }
  }
}
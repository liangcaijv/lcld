import java.util.Scanner;
//闰年
public class Case14_LeapYearWithWhile{
  public static void main(String[] args) {
    // 注意将这种可以在循环中重复利用的资源声明在循环的外面
    Scanner in = new Scanner(System.in);
    while(true){
        System.out.println("请输入一个大于1582的年份：" );
        int year  = in.nextInt();
        // 如果用户输入-1，终止循环
        if(year == -1){
            break;
        }
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
}
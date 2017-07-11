import java.util.Scanner;

public class Case23_Palindrome{
  public static void main(String[] args) {
    System.out.println("请输入一个字符串：");
    Scanner in = new Scanner(System.in);
    String str = in.nextLine();

    // 1. 循环怎么控制——何时退出，用什么变量来控制
    // 2. 控制条件
    // 3. 每次循环后，控制变量有何变化
    // 4. 最终要什么结果？是：否
    // 5. 怎么在循环中逐步得到结果
    boolean isPalindrome = false;
    int begin = 0,end = str.length()-1 ;
    
    while(begin<end && str.charAt(begin)==str.charAt(end)){  
      begin++;
      end--;
    }
    if(begin>=end){
      isPalindrome = true;
    }
    System.out.println(str+(isPalindrome?"":"不")+"是一个回文字符串");
  }
}
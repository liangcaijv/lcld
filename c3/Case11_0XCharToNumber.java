import java.util.Scanner;

public class Case11_0XCharToNumber{
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("请输入0~9、A~F或a~f间的一个字符：" );
    // char x  = in.nextChar();  // Scanner没有读入一个字符的方法
    String s = in.next();  // 用户输入的一个字符串
    char x = s.charAt(0);  // 截取第一个字符
    int result;
    if(x>=48 && x<=57){ // '0'~'9'
      System.out.println(x+"的十进制表示为：" + x);
    }else if(x>=97 && x<=102){ // 'a'~'f'
      System.out.println(x+"的十进制表示为：" + (x-'a'+10));
    }else if(x>=65 && x<=70){ //'A'~'F'
      System.out.println(x+"的十进制表示为：" + (x-'A'+10));
    }else{
      System.out.println("整不了……" );
    }
  }
}
import java.util.Scanner;

public class Case15_ChatWithGirlFriend{
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while(true){
      System.out.println("说吧：");
      String content = in.nextLine();
      
      if(content.equals("你又胖了")){
        System.out.println("滚！");
      }else if(content.equals("你是最美的！")){
        System.out.println("你这样说，人家会害羞呢……");
      }else if(content.equals("娘子，夜深了")){
        System.out.println("不用说了，先把灯关上吧！");
        break;
      }else{
        System.out.println("你说什么，我听不懂啦……");
      }
    }
  }

}
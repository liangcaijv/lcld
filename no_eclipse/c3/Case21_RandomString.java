import java.util.Scanner;

public class Case21_RandomString{
  public static void main(String[] args) {
    System.out.println("请输入一个长度：");
    Scanner in = new Scanner(System.in);
    int number = in.nextInt();

    String result = "";
    for(int i = 0;i<number;i++){    
      char c = (char)(Math.random()*(0xffff+1));
      if((c>='0' && c<='9') || (c>='a' && c<='z') || (c>='A' && c<='Z')){
        result+=c;
      }else{
        i--; // 废弃当前这次循环的成果
      }
    }

    int i  = 0;
    while(i<number){
      char c = (char)(Math.random()*(0xffff+1));
      if((c>='0' && c<='9') || (c>='a' && c<='z') || (c>='A' && c<='Z')){
        result+=c;
        i++;
      }
    }

    System.out.println("随机字符串是："+result);
  }
}
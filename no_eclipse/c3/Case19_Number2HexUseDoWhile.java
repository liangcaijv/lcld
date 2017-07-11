import java.util.Scanner;

public class Case19_Number2HexUseDoWhile{
  public static void main(String[] args) {
    System.out.println("请输入一个正整数：");
    Scanner in = new Scanner(System.in);
    int number = in.nextInt();

    //  这些变量，每次循环都会改变它们，每次循环也要利用上次循环之后这些变量的值
    // 这样的变量就应该放在循环之外来声明
    String hex = "";
    int shang = number;
    int yu;
    do{
      yu = shang % 16; // 余
      shang = shang / 16; // 商

      //----余数转char begin----
      char result;
      if(yu>9){
        result = (char)('A'+(yu-10));
      }else{
        result = (char)('0'+(yu-0));
      }
      //----余数转char end----
      hex = result + hex;  
    }while(shang != 0);
    
    System.out.println(number+"的16进制形式是："+hex);
    // yu = shang % 16; // 余 7
    // shang = shang / 16; // 商 0

    // // 判断 shang 为 0
    // if(yu>9){
    //   result = (char)('A'+(yu-10));
    // }else{
    //   result = (char)('0'+(yu-0));
    // } // result:7

    // hex = result + hex;

  }
}
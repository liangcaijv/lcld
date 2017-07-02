import java.util.Scanner;

public class Case18_Number2Binary{
  public static void main(String[] args) {
    int i = 0b0111_1111_1111_1111_1111_1111_1111_1111;
    System.out.println("int的最大整数是："+i);
    Scanner in = new Scanner(System.in);
    while(true){
      System.out.println("请输入一个整数：");
      int number = in.nextInt();
      // if(number == -1){
      //   break;
      // }
      int number2;
      if(number<0){
        number2 = (int)(Math.pow(2,31) + number);
      }else{
        number2 = number;
      }
      //  这些变量，每次循环都会改变它们，每次循环也要利用上次循环之后这些变量的值
      // 这样的变量就应该放在循环之外来声明
      String binary = "";
      int shang = number2;
      int yu;
      int count = 0;
      while(shang != 0){
        yu = shang % 2; // 余
        shang = shang / 2; // 商
        count++;
        if(count%4==0){
          binary = "_" + yu + binary; 
        }else{
          binary = yu + binary;   
        }
      }
      while(count<31){
        count++;
        if(count%4==0){
          binary = "_" + 0 + binary; 
        }else{
          binary = 0 + binary;   
        }
      }
      if (number<0) {
        binary = 1 + binary;
      }else {
        binary = 0 + binary;
      }
      System.out.println(number+"的2进制形式是："+binary);
    }
  }
}
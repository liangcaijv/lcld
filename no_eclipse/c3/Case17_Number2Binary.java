import java.util.Scanner;

public class Case17_Number2Binary{
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("请输入一个整数：");
    int number = in.nextInt();
    //  这些变量，每次循环都会改变它们，每次循环也要利用上次循环之后这些变量的值
    // 这样的变量就应该放在循环之外来声明
    String binary = "";
    int shang = number;
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
    System.out.println(number+"的2进制形式是："+binary);
    
  }
}
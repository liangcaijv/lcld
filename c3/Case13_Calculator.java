import java.util.Scanner;

public class Case13_Calculator{
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 接受第一个操作数
    double a = in.nextDouble();
    // 接受操作符
    String operator = in.next();
    // 接受第二个操作数
    double b = in.nextDouble();
    // 用于保存运算结果的变量
    double result = 0;
    switch(operator){
        case "+":
            result = a + b;
            break;
        case "-":
            result = a - b;
            break;
        case "*":
            result = a * b;
            break;
        case "/":
            result = a / b;
            break;
        case "%":
            result = a % b;
            break;  
        default:
            result = Double.NaN;  // NaN = not a number
    }
    System.out.printf("%.2f %s %.2f = %.2f \n",a,operator,b,result);
  }
}
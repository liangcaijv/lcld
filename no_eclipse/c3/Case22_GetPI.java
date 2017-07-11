
public class Case22_GetPI{
  public static void main(String[] args) {
    //4*(1-1/3+1/5...+Math.pow(-1,i+1)/2i-1)
    double result = 0;
    for(int i = 1; i<=100000;i++ ){
      result += Math.pow(-1,i+1) / (double)(2*i-1);
    }
    System.out.println(4*result);
  }
}
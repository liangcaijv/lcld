public class TestForLoop{
  public static void main(String[] args) {
    // for(int i = 0; i<100;i++){
    //   System.out.println("welcome to java "+i);
    // }
    for(int i = 0;i<10;i++){
     System.out.print("*"); 
    }
// for 循环的等价形式
    int i = 0;
    while(i<10){
     System.out.print("*");  
     i++;
    }
  }
}
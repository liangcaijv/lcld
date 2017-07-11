public class ArrayUtils{
  public static void print(int[][] arr){
    for (int a[] : arr) {
      for (int e : a) {
        System.out.print(e+"\t");        
      }
      System.out.println();        
    }
  }
}
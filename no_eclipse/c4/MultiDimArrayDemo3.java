//动态为二维数组的元素赋值
public class MultiDimArrayDemo3 {
  public static void main(String args[]) {
    double a[][] = new double[3][3];
	
    for(int i=0;i<a.length;i++){
		double[] row = a[i];
		
		for(int j=0;j<row.length;j++){
			a[i][j] = Math.random()*100;
		}
    }
	
	for(int i=0;i<a.length;i++){
		double[] row = a[i];//理解的一个难点
		/*for(int r:row){
			System.out.print(r+"\t");
		}*/
		
		for(int j=0;j<row.length;j++){
			System.out.print("a["+i+"]["+j+"] = "+a[i][j]+"  ");
		}

		System.out.println();
    }
  }
}
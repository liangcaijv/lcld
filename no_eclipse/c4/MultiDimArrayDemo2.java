//遍历二维数组
public class MultiDimArrayDemo2 {
  public static void main(String args[]) {
    int a[][] = {{1,2},{3,4,5,6},{7,8,9}};
	//数组都有length属性，代表数组的长度，一维数组的length指的是元素个数，二维数组的length指的是行数
    for(int i=0;i<a.length;i++){
		int[] row = a[i];//理解的一个难点
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

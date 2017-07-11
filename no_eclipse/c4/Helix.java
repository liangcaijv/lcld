public class Helix{
//	——产生二维数组
	int[][] generateArr(int i, int j){
		int[][] arr = new int[i][j];
		for(int m=0;m<i;m++){
			for(int n=0;n<j;n++){
				arr[m][n] = (int)(Math.random()*100+1);
			}
		}
		return arr;
	}
//——格式化输出二维数组
	void outputArr(int[][] arr){
		for(int[] a:arr){
			for(int s:a){
				System.out.print(s+"\t");
			}
			System.out.println();
		}
	}
//——按顺时针螺旋输出二维数组
	void helixOutput(int[][] arr){
		f(arr);
	}
	//去掉第一行并左旋转
	static void f(int[][] arr){
		if(arr.length==0)
			return ;
		
		for(int i=0;i<arr[0].length;i++){
			System.out.print(arr[0][i]+"\t");
		}
		int row = arr.length;
		int col = arr[0].length;
		int[][] newArr = new int[col][row-1];
		for(int i = 1;i<row;i++){
			for(int j=0;j<col;j++){
				newArr[col-j-1][i-1] = arr[i][j];
			}
		}
		f(newArr);
	}
	public static void main(String[] args){
		Helix h = new Helix();
		int[][] arr = h.generateArr(5,4);
		h.outputArr(arr);
		System.out.println("===============");
		//h.outputArr(f(arr));
		h.helixOutput(arr);
	}
}
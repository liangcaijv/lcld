public class ArrayDemo2{
	int[][] array = new int[5][3];//���Ԫ�أ�ÿ��Ԫ���ǳ���Ϊ3��int����
	int[][] array2 = new int[5][];//���Ԫ�أ�ÿ��Ԫ���ǳ���δ֪��int����
	int[][] array3 = {{2,4,3,5},{1},{12,13,14}};//��ά����ľ�̬��ʼ��
	void init(){
		// System.out.println(array[0]);
		// System.out.println(array2[0]);
		//array[0] = new int[4];
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				array[i][j] = i*j;
			}
		}		
		array2[0] = new int[]{2,3,4};
		array2[1] = new int[]{2,3,6,7,9};
		array2[2] = new int[]{2,3};
		array2[3] = new int[]{2,3,6,3};
		array2[4] = new int[]{2,3,7,4,6,3,4,12};		
		// array3 = new int[][]{};
	}
	void test(){
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				System.out.print(array[i][j]+"\t");
			}
			System.out.println();
		}
		
		System.out.println("=============================");
		for(int i=0;i<array2.length;i++){
			for(int j=0;j<array2[i].length;j++){
				System.out.print(array2[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("=============================");
		//foreach������ά����
		for(int[] a:array){
			for(int s:a){
				System.out.print(s+"\t");
			}
			System.out.println();
		}
	}
	public static void main(String[] args){
		ArrayDemo2 demo = new ArrayDemo2();
		demo.init();
		demo.test();
	}
	
	
}
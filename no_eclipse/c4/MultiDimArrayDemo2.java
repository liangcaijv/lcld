//������ά����
public class MultiDimArrayDemo2 {
  public static void main(String args[]) {
    int a[][] = {{1,2},{3,4,5,6},{7,8,9}};
	//���鶼��length���ԣ���������ĳ��ȣ�һά�����lengthָ����Ԫ�ظ�������ά�����lengthָ��������
    for(int i=0;i<a.length;i++){
		int[] row = a[i];//����һ���ѵ�
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

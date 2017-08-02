public class Case_Diamond {
	static void execute(int line){
		// int line = 11;//����
		//��ѭ��ǰ���  �߽�Ϊline/2+1
		for(int n=0;n<line/2+1;n++){
			//������ո���
			int kgs = line/2-n;	
			for(int i=0;i<kgs;i++){
				System.out.print(" ");
			}
			
			//�����*��
			int xhs = n*2+1;
			for(int i=0;i<xhs;i++){
				System.out.print("*");
			}	
			System.out.println();
		}
		//ѭ���°��
		for(int n=line/2;n>0;n--){
			//������ո���
			int kgs = line/2+1-n;
			
			for(int i=0;i<kgs;i++){
				System.out.print(" ");
			}
			
			//�����*��
			int xhs = 2*n-1;
			for(int i=0;i<xhs;i++){
				System.out.print("*");
			}	
			System.out.println();
		}
	}

}
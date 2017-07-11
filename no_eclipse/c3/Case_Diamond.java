public class Diamond{
	static void execute(int line){
		// int line = 11;//行数
		//先循环前半段  边界为line/2+1
		for(int n=0;n<line/2+1;n++){
			//计算出空格数
			int kgs = line/2-n;	
			for(int i=0;i<kgs;i++){
				System.out.print(" ");
			}
			
			//计算出*数
			int xhs = n*2+1;
			for(int i=0;i<xhs;i++){
				System.out.print("*");
			}	
			System.out.println();
		}
		//循环下半段
		for(int n=line/2;n>0;n--){
			//计算出空格数
			int kgs = line/2+1-n;
			
			for(int i=0;i<kgs;i++){
				System.out.print(" ");
			}
			
			//计算出*数
			int xhs = 2*n-1;
			for(int i=0;i<xhs;i++){
				System.out.print("*");
			}	
			System.out.println();
		}
	}

}
public class Case_ShuiXianHua{
	public static void main(String[] args){
		/* for(int i=100;i<1000;i++){
			int baiwei = i/100;
			int shiwei = (i/10)%10;
			int gewei = i%10;
			
			if(baiwei*baiwei*baiwei+shiwei*shiwei*shiwei+gewei*gewei*gewei == i){
				System.out.println(i);
			}
			
		} */
		
		
		for(int i=0;i<=9;i++){
			for(int j = 0;j<=9;j++){
				for(int k = 1;k<=9;k++){
					if(i*i*i+j*j*j+k*k*k == i+j*10+k*100)
						System.out.println(" "+k+j+i);
				}
			}
		}
		
	}
}
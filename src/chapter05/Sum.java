package chapter05;

public class Sum {
	public static void main(String[] args) {
		int num = 100;
		int sum = 0;
		for (int i = 0; i < num; i++) {
			if(num%2!=0){
				continue;
			}
			sum+=i;
		}
		System.out.println(sum);
	}
}

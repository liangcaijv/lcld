// if语句
public class TestIf {
	public static void main(String[] args) {
		if (false) {
			System.out.println(1);		
		}else if (false) {
			System.out.println(2);		
		}else if (true) {
			System.out.println(3);		
		}else if (true) {
			System.out.println(4);		
		}else{
			System.out.println(5);		
		}		

		int x = 12;
		int y = 30;
		if(x > 4) {
		  System.out.println("test 1");
		}else if(x > 9) {
			System.out.println(y);
		  System.out.println("test 2");
		}else {
		  System.out.println("test 3");
		}

	}
}
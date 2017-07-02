public class TestChar{
	public static void main(String[] args) {
		System.out.print("A"+'\u0061'+"B");
		char ch = (char)65.25; //0xAB0041;
		char ch1 = '\u0041';
		char ch2 = 65; //0x0041
		char ch3 = 0xFFFF;
		System.out.println("char ch:" + ch);
		System.out.println("char ch1:" + ch1);
		System.out.println("char ch2:" + ch2);


		char ch4 = '2' + '3';
		char ch5 = '2' + 3;
		System.out.println("char ch4:" + ch4);
		System.out.println("char ch5:" + ch5);			
	}
}
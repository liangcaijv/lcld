/**
* switch 与 String
* jdk7开始支持String表达式作为switch的参数
*/
public class TestSwitch2{
	public static void main(String[] args) {
		String month = "Jan";
		switch (month) {
			case "Jan":
				System.out.println("January");
				break;
			case "Feb":
				System.out.println("February");
				break;
		}
	}
}
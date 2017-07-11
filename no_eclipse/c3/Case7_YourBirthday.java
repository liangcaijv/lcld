import java.util.Scanner;
public class Case7_YourBirthday{
	public static void main(String[] args) {
		String set1 =
		" 1 3 5 7\n" +
		" 9 11 13 15\n" +
		"17 19 21 23\n" +
		"25 27 29 31";	

		String set2 =
		" 2 3 6 7\n" +	
		"10 11 14 15\n" +
		"18 19 22 23\n" +
		"26 27 30 31";

		String set3 =
		" 4 5 6 7\n" +
		"12 13 14 15\n" +
		"20 21 22 23\n" +
		"28 29 30 31";

		String set4 =
		" 8 9 10 11\n" +
		"12 13 14 15\n" +
		"24 25 26 27\n" +
		"28 29 30 31";

		String set5 =
		"16 17 18 19\n" +
		"20 21 22 23\n" +
		"24 25 26 27\n" +
		"28 29 30 31";

		int day = 0;	

  // Create a Scanner
		Scanner input = new Scanner(System.in);

    // Prompt the user to answer questions
		System.out.print("你的生日(号数)在这个集合里面吗?\n");
		System.out.print(set1);
		System.out.print("\n是，请输入1；否请输入0: ");
		int answer = input.nextInt();
		day += answer * 1;

    // Prompt the user to answer questions
		System.out.print("\n你的生日(号数)在这个集合里面吗?\n");
		System.out.print(set2);
		System.out.print("\n是，请输入1；否请输入0: ");
		answer = input.nextInt();
		day += answer * (1 << 1);

    // Prompt the user to answer questions
		System.out.print("你的生日(号数)在这个集合里面吗??\n");
		System.out.print(set3);
		System.out.print("\n是，请输入1；否请输入0: ");
		answer = input.nextInt();
		day += answer * (1 << 2);

    // Prompt the user to answer questions
		System.out.print("\n你的生日(号数)在这个集合里面吗?\n");
		System.out.print(set4);
		System.out.print("\n是，请输入1；否请输入0: ");
		answer = input.nextInt();
		day += answer * (1 << 3);

    // Prompt the user to answer questions
		System.out.print("\n你的生日(号数)在这个集合里面吗?\n");
		System.out.print(set5);
		System.out.print("\n是，请输入1；否请输入0: ");
		answer = input.nextInt();
		day += answer * (1 << 4);

		
		System.out.println("\n你的生日所在天是 " + day + "号!");
	}
}
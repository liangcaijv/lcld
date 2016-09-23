package chapter02;

/**
 * @描述 已知Jack的身高为180cm，rose比Jack矮10cm,lucy比rose高8cm，输出三人的身高到控制台
 */
public class MathApplication {

	public static void main(String[] args) {
		int heightOfJack = 180;//声明Jack身高变量
		int heightOfLucy = heightOfJack - 10;//声明lucy身高，引用jack身高变量并计算
		int heightOfRose = heightOfLucy + 8;//声明Rose身高，引用Lucy身高变量并计算
		System.out.println("Jack的身高是：" + heightOfJack + "cm");//输出Jack身高
		System.out.println("lucy的身高是：" + heightOfLucy + "cm");//输出Lucy身高
		System.out.println("rose的身高是：" + heightOfRose + "cm");//输出Rose身高
	}

}

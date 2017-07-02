// 运算符
public class TestOperator{
	public static void main(String[] args){
		int i = 3;
		double j = 2;
		double j1 = 2.3;
	  System.out.println("======算术运算符======");	
		System.out.println(i/j);

		int i2 = i++;
		System.out.println("i=="+i);
		System.out.println("i2=="+i2);

		System.out.println("======关系运算符和逻辑运算符======");
		System.out.println("i!=i2?"+(i!=i2));
		System.out.println("i != i2 && j1 > j  ? " + ((i != i2) && (j1 > j)));
		System.out.println("i != i2 || j1 < j  ? " + ((i != i2) || (j1 < j))); //true || false
		System.out.println("!(i != i2)" + (!(i != i2) )); 

		boolean result = (i<j) && (++j == 3);
		// j 的值 是多少呢？
		System.out.println("短路运算之后j的值是多少=="+j);

		System.out.println("======三元运算符======");
		int score = 85;
		String isOK = score >= 60 ? (score >= 85 ? "优秀" : "合格") : "不及格";
		System.out.println("score="+score+"时，我们认为成绩："+isOK);

		System.out.println("======按位运算符======");
		int num = 86;
		System.out.println(num + "是：" + (((num&1)==0) ? "偶数" : "奇数"));

		System.out.println( num +"的第5位上的二进制数是：" + (((num & (1<<4))>>4)==0?"0":"1") );
		System.out.println( num +"的第5位上的二进制数是：" + ((((num>>4)&1)==0)?"0":"1") );

		int num1 = 10;
		int num2 = 20;

		num1 = num1^num2;
		num2 = num1^num2;
		num1 = num1^num2;

		System.out.println("num1=="+num1+", num2=="+num2);


		num2 = -20;

		System.out.println(num2 + "的绝对值是：" + ((num2 ^ (num2>>31)) + (num2 >>> 31)) );

	}

}
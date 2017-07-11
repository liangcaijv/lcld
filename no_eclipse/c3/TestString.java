public class TestString{
	public static void main(String[] args) {
		// 1. 得到或者创建对象，并赋值给一个句柄（引用类型的变量）
		String name = "abc123";
		// 2. 在句柄上调用各种方法，如果方法有参数，需要传递参数
		// 3. 如果方法有返回值，我们可以接收这个返回值
		int len = name.length();
		char charAt1 = name.charAt(1);
		String upperName = name.toUpperCase();
		String lowerName = name.toLowerCase();
		
		System.out.println("lengthOfName=" + len);
		System.out.println("name's char at index 1 =" + charAt1);
		System.out.println("name的大写形式是：" + upperName);
		System.out.println("name的小写形式是：" + lowerName);
	}
}
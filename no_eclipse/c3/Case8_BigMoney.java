public class Case8_BigMoney {
	public static void main(String[] args) {
		String result = "";

		double money = 9128732.12;

		int fen = (int) (money * 100); // 多少分
		int wan = fen / 1000000; // 万位上的数字
		result += (wan + "萬");

		fen = fen % 1000000;
		int qian = fen / 100000;
		result += (qian + "仟");

		fen = fen % 100000;
		int bai = fen / 10000;
		result += (bai + "佰");

		fen = fen % 10000;
		int shi = fen / 1000;
		result += (shi + "拾");

		fen = fen % 1000;
		int yuan = fen / 100;
		result += (yuan + "圆");

		fen = fen % 100;
		int jiao = fen / 10;
		result += (jiao + "角");
		
		fen = fen % 10;
		int fenfen = fen;
		result += (fenfen + "分");

		System.out.println(money + "的大写形式是：" + result);
	}
}
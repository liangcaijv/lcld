package org.lanqiao.java8demo.bignum;

public class BigMoney {
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		// 98732.12
		// double money = in.nextDouble();
		double money = 90032.12;
		int fen = (int) (money * 100);
		int wan = fen / 1000000;
		System.out.print(number2ChineseNumber(wan) + "萬");
		fen = fen % 1000000;
		int qian = fen / 100000;
		System.out.print(number2ChineseNumber(qian) + "仟");
		fen = fen % 100000;
		int bai = fen / 10000;
		System.out.print(number2ChineseNumber(bai) + "佰");
		fen = fen % 10000;
		int shi = fen / 1000;
		System.out.print(number2ChineseNumber(shi) + "拾");
		fen = fen % 1000;
		int yuan = fen / 100;
		System.out.print(number2ChineseNumber(yuan) + "圆");
		fen = fen % 100;
		int jiao = fen / 10;
		System.out.print(number2ChineseNumber(jiao) + "角");
		fen = fen % 10;
		int fenfen = fen;
		System.out.print(number2ChineseNumber(fenfen) + "分");
	}
	static String number2ChineseNumber(int num){
		switch (num) {
		case 1:
			return "壹";
		case 2:
			return "贰";
		case 3:
			return "叁";
		case 4:
			return "肆";
		case 5:
			return "伍";
		case 6:
			return "陆";
		case 7:
			return "柒";
		case 8:
			return "捌";
		case 9:
			return "玖";
		case 10:
			return "拾";
		default:
			return "零";
		}
	}
}

package chapter03;

public class App02 {
	public static void main(String[] args) {
		//商品原价=折后的售价除以折扣比例
		double originalPrice = 52/0.8;//价格是有小数位的，所以用浮点类型来声明,尝试用float来声明，看看效果
		System.out.println("商品原价为" + originalPrice);
		//成本价格=定价/成本溢价比
		double costPrice = originalPrice/(1+0.3);
		System.out.println("商品的成本为："+costPrice);
		//75折后价格-成本=商品利润
		double profit = (int)(originalPrice*0.75)-costPrice;//originalPrice*0.75的结果为double，强制转化为int会舍去小数位（注意不是四舍五入），
		System.out.println("75折后的商品利润为：" + profit);
		
	}
}

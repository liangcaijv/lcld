package org.lanqiao.javaapitools;

import java.math.BigDecimal;

public class BigDecimalDemo {
	public static void main(String[] args) {
		BigDecimal bd1 = new BigDecimal("1.234534");
		BigDecimal bd2 = new BigDecimal("-1.234534");
		// ---------向远离0的方向移动,先截断,保留位+1-------------------
		System.out.println("ROUND_UP");
		System.out.println(bd1.setScale(3, BigDecimal.ROUND_UP).doubleValue());
		System.out.println(bd2.setScale(3, BigDecimal.ROUND_UP).doubleValue());
		System.out.println(bd1.setScale(4, BigDecimal.ROUND_UP).doubleValue());
		System.out.println(bd2.setScale(4, BigDecimal.ROUND_UP).doubleValue());
		// ---------向靠近0的方向移动,直接截断-------------------
		System.out.println("ROUND_DOWN");
		System.out.println(bd1.setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
		System.out.println(bd2.setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
		System.out.println(bd1.setScale(4, BigDecimal.ROUND_DOWN).doubleValue());
		System.out.println(bd2.setScale(4, BigDecimal.ROUND_DOWN).doubleValue());

		// ---------向正无穷方向移动,正数截断+1,负数截断-------------------
		System.out.println("ROUND_CEILING");
		System.out.println(bd1.setScale(3, BigDecimal.ROUND_CEILING).doubleValue());
		System.out.println(bd2.setScale(3, BigDecimal.ROUND_CEILING).doubleValue());
		System.out.println(bd1.setScale(4, BigDecimal.ROUND_CEILING).doubleValue());
		System.out.println(bd2.setScale(4, BigDecimal.ROUND_CEILING).doubleValue());
		
	// ---------向负无穷方向移动,正数截断,负数截断+1-------------------
			System.out.println("ROUND_FLOOR");
			System.out.println(bd1.setScale(3, BigDecimal.ROUND_FLOOR).doubleValue());
			System.out.println(bd2.setScale(3, BigDecimal.ROUND_FLOOR).doubleValue());
			System.out.println(bd1.setScale(4, BigDecimal.ROUND_FLOOR).doubleValue());
			System.out.println(bd2.setScale(4, BigDecimal.ROUND_FLOOR).doubleValue());
			
			// ---------四舍五入-------------------
			System.out.println("ROUND_HALF_UP");
			System.out.println(bd1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
			System.out.println(bd2.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
			System.out.println(bd1.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
			System.out.println(bd2.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());

			// ---------非严格五舍六入(恰好为5舍,大于0.5就入)-------------------
			System.out.println("ROUND_HALF_DOWN");
			System.out.println(bd1.setScale(3, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			System.out.println(bd2.setScale(3, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			System.out.println(bd1.setScale(4, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			System.out.println(bd2.setScale(4, BigDecimal.ROUND_HALF_DOWN).doubleValue());

			// ---------保留位是奇数,则四舍五入,保留位为偶数则5舍6入-------------------
			bd1 = new BigDecimal("0.235");
			bd2 = new BigDecimal("0.245");
			System.out.println("ROUND_HALF_EVEN");
			System.out.println(bd1.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
			System.out.println(bd2.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
			
			// ---------断言精确,任何不精确的舍和入,都会导致异常抛出---------------------
			System.out.println(bd1.setScale(3, BigDecimal.ROUND_UNNECESSARY).doubleValue());
			
	}
}

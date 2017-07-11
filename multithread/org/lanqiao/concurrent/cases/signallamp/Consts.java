package org.lanqiao.concurrent.cases.signallamp;

import java.util.Random;

public class Consts {
	public static final String N2S="N2S";
	public static final String N2W="N2W";
	public static final String N2E="N2E";
	public static final String S2N="S2N";
	public static final String S2W="S2W";
	public static final String S2E="S2E";
	public static final String E2W="E2W";
	public static final String E2S="E2S";
	public static final String E2N="E2N";
	public static final String W2E="W2E";
	public static final String W2N="W2N";
	public static final String W2S="W2S";
	public static final String[] directions = {N2S,N2W,N2E,S2N,S2W,S2E,E2W,E2S,E2N,W2E,W2N,W2S};
	/**
	 * �����ȡһ������
	 * @return
	 */
	public static final String getRandomDirection(){
		return directions[new Random().nextInt(12)];
	}
}

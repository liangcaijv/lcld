package org.lanqiao.oop.extend.cases;

import java.util.Scanner;

public class DictFace {
	private AbstractEn2CnDict dict;

	public DictFace() {
	}

	public DictFace(AbstractEn2CnDict dict) {
		super();
		this.dict = dict;
	}

	public void start() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("�����뵥��,exit�˳���ѯ:");
			String cn = scanner.nextLine();
			if (cn.equalsIgnoreCase("exit")) {
				break;
			}
			System.out.println("��ѯ���:" + dict.enToCn(cn));
		}
	}

}

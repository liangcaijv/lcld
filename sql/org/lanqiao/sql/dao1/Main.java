package org.lanqiao.sql.dao1;

import java.sql.Date;

public class Main {
	public static void main(String[] args) {
		EmpDao dao = new EmpDaoImpl();
//		dao.save(new Employee("zhang", "san", "zhangsan@163.com", "13000000000", new Date(2000, 1, 1), 2000));
		System.out.println(dao.findById(1));
		System.out.println(dao.findAll());
	}
}

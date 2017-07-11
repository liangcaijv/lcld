package org.lanqiao.commons.dbutils;

import java.util.Scanner;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static EmpDao dao = new EmpDaoImpl();

	public static void main(String[] args) {
		boolean flag=true;
		while (flag) {
			help();
			int command = getIntCommand();
			switch (command) {
			case 1:
				add();
				break;
			case 2:
				del();
				break;
			case 3:
				update();
				break;
			case 4:
				find();
				break;
			default:
				flag=false;
			}
		}
	}

	private static void find() {
		System.out.println("请输入id或直接回车查看全部");
		String s = scanner.nextLine();
		if(s==null||s.length()==0){
			System.out.println(dao.findAll());
		}else{
			try {
				int id = Integer.parseInt(s);
				System.out.println(dao.findById(id));				
			} catch (Exception e) {
				System.out.println("不正确的id");
			}
		}
	}

	private static void update() {
		System.out.println("请输入id和新信息——,分隔");
		String s = scanner.nextLine();
		Employee emp = new Employee();
		String[] info = s.split(",");
		try {
			emp.setEmployee_id(Integer.parseInt(info[0]));
		} catch (Exception e) {
			System.out.println("请输入id");
			return;
		}
		emp.setFirst_name(info[1]);
		emp.setLast_name(info[2]);
		dao.update(emp);
	}

	private static void del() {
		System.out.println("请输入id");
		int id = getIntCommand();
		dao.delete(new Employee(id));
		System.out.println("删除成功");
	}

	private static void add() {
		System.out.println("请输入first_name,last_name,email,用,分隔");
		String s = scanner.nextLine();
		String[] info = s.split(",");
		Employee emp = new Employee();
		emp.setFirst_name(info[0]);
		emp.setLast_name(info[1]);
		
		dao.save(emp);
		System.out.println("新增成功");
	}

	private static int getIntCommand() {
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("请输入正确的命令");
			return getIntCommand();
		}
	}

	private static void help() {
		System.out.println("*****************************************");
		System.out.println("*\t欢迎进入员工管理系统\t*");
		System.out.println("*****************************************");
		System.out.println("1.新增\t2.删除\t3.修改\t4查询\t5退出");
	}

}

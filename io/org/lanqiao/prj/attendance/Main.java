package org.lanqiao.prj.attendance;

import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
	private static ISysManager sysManager = new SysManager();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		String command = "";
		int int_command = 0;
		while (true) {
			help();
			command = scanner.nextLine();
			int_command = Integer.parseInt(command);
			switch (int_command) {
			case 1:// 管理员操作
				System.out.println("请输入管理员密码");
				String pwd = scanner.nextLine();// 管理员密码
				if (sysManager.checkAdmin(pwd)) {
					adminOperate();
				}
				break;
			case 2:// 用户操作
				userLogin();
				break;

			default:
				System.exit(0);
				break;
			}

		}
	}

	private static void userLogin() {
		System.out.println("请输入用户名");
		String name = scanner.nextLine();// 用户名
		System.out.println("请输入密码");
		String pwd = scanner.nextLine();// 密码

		// 根据用户名和密码去查询是否有相应的记录
		boolean b = sysManager.checkUser(name, pwd);
		if (b) {
			System.out.println("登录成功！欢迎你，"
					+ name
					+ ",现在是"
					+ DateFormat.getDateInstance(DateFormat.LONG).format(
							new Date()));
			userOp(name);//如果登录成功，进入用户的下一级菜单
		} else {
			System.out.println("登录失败！");

		}
	}

	private static void userOp(String name) {
		System.out.println("1.上班打卡\t2.下班打卡\t3.查询\t任意键注销");
		String command = scanner.nextLine();
		int int_command = Integer.parseInt(command);
		User user = sysManager.findUserByName(name);//通过真实名查找到系统中的user
		switch (int_command) {
		case 1:// 上班打卡
			user.addRecord(0);
			sysManager.save();//持久化保存
			break;
		case 2:// 下班打卡
			user.addRecord(1);
			sysManager.save();//持久化保存
			break;
		case 3://显示记录
			System.out.println(user.showRecord());
			break;
		default:
			break;
		}
	}

	private static void help() {
		System.out.println("*****************************************");
		System.out.println("*\t\t欢迎进入管理系统\t\t*");
		System.out.println("*****************************************");
		System.out.println("请选择登陆类型：\t1.管理员\t2.用户\t3.退出");
	}

	private static void helpAdmin() {
		System.out.println("1.注册新用户\t2.查询所有用户信息\t3.其他任意键返回上层");

	}

	private static void adminOperate() {// 管理原操作
		helpAdmin();

		// 等待管理员输入输入命令
		String command = scanner.nextLine();
		int int_command = Integer.parseInt(command);// 1.注册新用户2.查询所有用户信息3.其他任意键返回上层
		switch (int_command) {
		case 1:
			// 输入用户信息
			System.out.println("请输入要添加的用户信息:");
			System.out.println("请输入用户名:");
			String username = scanner.nextLine();
			System.out.println("请输入真实姓名");
			String name = scanner.nextLine();
			System.out.println("请输入没密码:");
			String password = scanner.nextLine();
			System.out.println("请输入确认密码:");
			String passwordAgain = scanner.nextLine();
			if (password.equals(passwordAgain)) {// 如果输入正确，添加用户
				User u = new User(username, name, password);
				try {
					sysManager.add(u);
					System.out.println("注册成功");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			} else {
				System.out.println("两次输入的密码不一样");
			}
			adminOperate();
			break;
		case 2:
			System.out.println(sysManager.list());
			adminOperate();
			break;
		default:
			break;
		}
	}
}

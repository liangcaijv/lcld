package org.lanqiao.prj.feiq.action;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.lanqiao.prj.feiq.exception.RepetitionException;
import org.lanqiao.prj.feiq.service.GroupManager;
import org.lanqiao.prj.feiq.service.IGroupManager;
import org.lanqiao.prj.feiq.vo.Friends;
import org.lanqiao.prj.feiq.vo.Group;

public class Main {
	private static File f = new File("friends.txt");
	private static IGroupManager gManager;
	static {
		try {
			gManager = new GroupManager(f);
		} catch (IOException e) {
			System.out.println("无法创建文件：" + e.getMessage());
		}

	}
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		sayHello();
		while (true) {
			sayCommand();
			int command = getIntCommand("命令");
			switch (command) {
			case 1:
				groupManage();
				break;
			case 2:
				friendsManage();
				break;
			default:
				break;
			}
		}
	}

	private static void friendsManage() {
		System.out.println("1.增加好友2.删除好友3.修改好友4.列出好友信息5.返回");
		int command = getIntCommand("命令");
		switch (command) {
		case 1:
			addFriend();
			break;
		case 2:
//			delGroup();
			break;
		case 3:
			
			break;
		case 4:
			listFriend();
			break;
		default:
			break;
		}
	}

	private static void listFriend() {
		System.out.println("1.列出所有2.按组列出");
		int command = getIntCommand("命令");
		if(command==1){
			try {
				System.out.println(gManager.listAll());
			} catch (Exception e) {
			}
		}else{
			int groupId = getIntCommand("组的id");
			try {
				System.out.println(gManager.listFriendsByGroup(groupId).toString());
			} catch (Exception e) {
				System.out.println("组信息错误，请重新选择");
				listFriend();
			}
		}
	}

	private static void addFriend() {
		int id = getIntCommand("好友id");
		String name = getStringCommand("好友姓名");
		int age = getIntCommand("好友年龄");
		int groupId = selectGroup();
		
		Friends f = new Friends(id, name, age); 
		String ip = getStringCommand("ip");
		f.setIp(ip);
		try {
			gManager.addFriendsToGroup(f, groupId);
		} catch (Exception e) {
			System.err.println("添加成功");
			addFriend();
		}
		System.err.println("添加成功");
	}

	private static int selectGroup() {

		int command = getIntCommand("组id");
		return command;
	}

	private static String getStringCommand(String type) {
		System.out.println("请输入"+type+":");
		
		return scanner.nextLine();
	}

	private static void groupManage() {
		System.out.println("1.增加组2.删除组3.修改组4.列出组信息5.返回");
		int command = getIntCommand("命令");
		switch (command) {
		case 1:
			addGroup();
			break;
		case 2:
			delGroup();
			break;
		case 3:
			break;
		case 4:
			listGroup();
			break;
		default:
			break;
		}
	}

	private static void listGroup() {
		//得到功能类返回的字符串
		System.out.println(gManager.list());
	}

	private static void delGroup() {
		int id = getIntCommand("组id");
		Group g=new Group(id);
		gManager.del(g);
	}

	private static void addGroup() {
		
		int id = getIntCommand("组id");
		System.out.println("输入组name");
		String name = scanner.nextLine();
		//调用功能类进行新增
		try {
			gManager.add(new Group(id, name));
		} catch (RepetitionException e) {
			addGroup();
		}
	}

	private static int getIntCommand(String type) {
		System.out.println("请输入"+type+"：");
		try {
			int command = Integer.parseInt(scanner.nextLine());
			return command;
		} catch (NumberFormatException e) {
			System.out.println("请输入正确的命令");
			return getIntCommand(type);
		}
	}

	private static void sayCommand() {
		System.out.println("1.组管理2.好友管理3.退出");
	}

	private static void sayHello() {
		System.out.println("欢迎进入通讯录程序！");

	}

}

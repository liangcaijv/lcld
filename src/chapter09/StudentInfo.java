package chapter09;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentInfo {
	
	//学号
	private String no;

	//姓名
	private String name;
	
	//专业
	private String major;
	
	
	//getter and setter
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	
	@Override
	public String toString() {
		return "学生信息 [no=" + this.no + ", name=" + this.name + ", major=" + this.major + "]";
	}

	public static void main(String[] args) {
		//声明可变长容器
		ArrayList<StudentInfo> container = new ArrayList<StudentInfo>();
		//声明控制台信息捕获器
		Scanner scanner = new Scanner(System.in);
		
		//循环获取控制台数据
		while(true){
			//获取学号
			System.out.println("请输入学生学号...");
			String no = scanner.next();
			
			//如果学号为-1,则打印容器内所有内容，终止循环
			if(no.equals("-1")){
				break;
			}
			
			//获取姓名
			System.out.println("请输入学生姓名...");
			String name = scanner.next();
			
			//获取专业
			System.out.println("请输入学生专业...");
			String major = scanner.next();
			
			//创建学生信息实例
			StudentInfo info = new StudentInfo();
			
			info.setNo(no);//设置学号信息
			info.setName(name);//设置姓名信息
			info.setMajor(major);//设置专业信息
			
			container.add(info);
		}
		
		//循环遍历ArrayList
		for (int i = 0; i < container.size(); i++) {
			System.out.println(container.get(i));
		}
	}
}


package chapter09;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentInfo {
	
	//ѧ��
	private String no;

	//����
	private String name;
	
	//רҵ
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
		return "ѧ����Ϣ [no=" + this.no + ", name=" + this.name + ", major=" + this.major + "]";
	}

	public static void main(String[] args) {
		//�����ɱ䳤����
		ArrayList<StudentInfo> container = new ArrayList<StudentInfo>();
		//��������̨��Ϣ������
		Scanner scanner = new Scanner(System.in);
		
		//ѭ����ȡ����̨����
		while(true){
			//��ȡѧ��
			System.out.println("������ѧ��ѧ��...");
			String no = scanner.next();
			
			//���ѧ��Ϊ-1,���ӡ�������������ݣ���ֹѭ��
			if(no.equals("-1")){
				break;
			}
			
			//��ȡ����
			System.out.println("������ѧ������...");
			String name = scanner.next();
			
			//��ȡרҵ
			System.out.println("������ѧ��רҵ...");
			String major = scanner.next();
			
			//����ѧ����Ϣʵ��
			StudentInfo info = new StudentInfo();
			
			info.setNo(no);//����ѧ����Ϣ
			info.setName(name);//����������Ϣ
			info.setMajor(major);//����רҵ��Ϣ
			
			container.add(info);
		}
		
		//ѭ������ArrayList
		for (int i = 0; i < container.size(); i++) {
			System.out.println(container.get(i));
		}
	}
}


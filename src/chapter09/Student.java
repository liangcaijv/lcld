package chapter09;

import java.util.ArrayList;

import chapter06.Array;

public class Student {
	
	private String name;
	
	public Student(String name) {
		this.name = name;
	}
	
	public void speak(){
		System.out.println("hello �ҽ�"+ name);
	}
	
	public static void main(String[] args) {
		ArrayList<Student> s = new ArrayList<Student>();
		ArrayPro arrayPro = new ArrayProImpl();
		Student student1 = new Student("����");
		Student student2 = new Student("����");
		Student student3 = new Student("����");
		arrayPro.add(student1);
		arrayPro.add(student2);
		arrayPro.add(student3);
		Student student = (Student) arrayPro.get(0);
		student.speak();
	}
}


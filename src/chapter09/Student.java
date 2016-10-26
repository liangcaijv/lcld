package chapter09;

import java.util.ArrayList;

import chapter06.Array;

public class Student {
	
	private String name;
	
	public Student(String name) {
		this.name = name;
	}
	
	public void speak(){
		System.out.println("hello 我叫"+ name);
	}
	
	public static void main(String[] args) {
		ArrayList<Student> s = new ArrayList<Student>();
		ArrayPro arrayPro = new ArrayProImpl();
		Student student1 = new Student("张三");
		Student student2 = new Student("李四");
		Student student3 = new Student("王五");
		arrayPro.add(student1);
		arrayPro.add(student2);
		arrayPro.add(student3);
		Student student = (Student) arrayPro.get(0);
		student.speak();
	}
}


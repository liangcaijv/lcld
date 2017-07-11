package org.lanqiao.iodemo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 示范对象流的使用
 * 
 * @author zheng last modified:2012-7-16
 * 
 */
public class ObjIO {
	
	public void writerPerson(File f, Person... p) {
		
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(f));
			for (Person p1 : p)
				out.writeObject(p1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out)
				try {
					out.close();
				} catch (IOException e) {
				}
		}
	}

	public List<Person> readPerson(File f) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(f));
			List<Person> list = new ArrayList<Person>();
			while (true) {
				try {
					Person p = (Person) in.readObject();
					list.add(p);
				} catch (EOFException e) {//读完了,EOFException是一个运行期异常，不要求捕获，但我们要控制读，需要手工捕获
					break;
				}
			}
			return list;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (null != in)
				try {
					in.close();
				} catch (IOException e) {
				}
		}
		return null;
	}

	public static void main(String[] args) {
		ObjIO demo = new ObjIO();
		demo.writerPerson(new File("d:/a.txt"), new Person("zhangsan"),new Person("lisi"),new Person("wangwu"),new Person("zhaoliu"));
		List<Person> p = demo.readPerson(new File("d:/a.txt"));
		System.out.println(p);

	}
}

class Person implements Serializable {// 标识型接口
	private String name;

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
}
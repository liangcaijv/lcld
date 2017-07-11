package org.lanqiao.prj.feiq.vo;

import java.io.Serializable;

public class Friends implements Serializable{
	
	private static final long serialVersionUID = -695904116982103139L;
	private int id;
	private String name;
	private int age;
	private Group group;
	private String ip;
	public Friends() {
	
	}
	
	public Friends(int id) {
		super();
		this.id = id;
	}

	public Friends(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public Friends(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Friends(int id, String name, int age, Group group) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.group = group;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	@Override
	public String toString() {
		return "Friends [id=" + id + ", name=" + name + ", age=" + age+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Friends other = (Friends) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}	

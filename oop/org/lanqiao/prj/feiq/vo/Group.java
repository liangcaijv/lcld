package org.lanqiao.prj.feiq.vo;

import java.io.Serializable;
import java.util.*;

public class Group implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2792653540993716872L;
	private int id;
	private String name;
	
	private Set<Friends> friends = new HashSet<Friends>();
	public Group() {
	}
	public Group(int id) {
		super();
		this.id = id;
	}
	
	public Group(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + "]";
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
		Group other = (Group) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public Set<Friends> getFriends() {
		return friends;
	}
	public void setFriends(Set<Friends> friends) {
		this.friends = friends;
	}
	
	
	
}

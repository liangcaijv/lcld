package org.lanqiao.prj.attendance;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.lanqiao.javaapitools.DateUtil;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1071337835580141343L;
	private String username;
	private String name;// 真实姓名
	private String pwd;
	private Set<Record> records = new HashSet<Record>();

	public User() {
	}

	public User(String username, String name, String pwd) {
		super();
		this.username = username;
		this.name = name;
		this.pwd = pwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void addRecord(int type) {
		records.add(new Record(type, new Date()));
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", pwd=" + pwd
				+ "]";
	}

	public String showRecord() {
		StringBuilder sb = new StringBuilder();
		for (Record r : records) {
			sb.append(r.getType() == 0 ? "上班签到" : "下班签到").append("\t")
					.append(DateUtil.format1(r.getDate())).append("\n");
		}
		return sb.toString();
	}

}

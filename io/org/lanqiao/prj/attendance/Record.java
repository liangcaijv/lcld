package org.lanqiao.prj.attendance;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable{
	private int type;
	private Date date;
	public Record() {
	}
	public Record(int type, Date date) {
		super();
		this.type = type;
		this.date = date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}

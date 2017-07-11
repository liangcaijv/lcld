package org.lanqiao.concurrent.cases.signallamp;
/**
 * �źŵ�
 * @author zhengwei
 *
 */
public class Lamp {
	private String direction;
	private boolean status;
	public Lamp(String direction, boolean status) {
		super();
		this.direction = direction;
		this.status = status;
	}
	
	public synchronized void open(){
		status = true;
	}
	public synchronized void close(){
		status = false;
	}
	public synchronized boolean isOpen(){
		return status;
	}
}

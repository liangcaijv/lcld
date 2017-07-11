package org.lanqiao.prj.attendance;

public interface ISysManager {
	void add(User user) throws Exception;
	String list();
	void save();
	public boolean checkUser(String name,String pwd);
	public boolean checkAdmin(String pwd);
	User findUserByName(String name);
}

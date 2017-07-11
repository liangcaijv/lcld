package org.lanqiao.prj.attendance;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SysManager implements ISysManager{
	private List<User> userList = new ArrayList<User>();
	private File file = new File("users.txt");
	public SysManager() {
		init();
	}
	private void init() {
		//如果文件不存在，新建该文件
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//读取文件中的user信息，并添加到userList中
		ObjectInputStream in = null;
		try {
			in  = new ObjectInputStream(new FileInputStream(file));
			while(true){
				try{
					User user = (User) in.readObject();
					userList.add(user);
				}catch (EOFException e) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (EOFException e) {
			//首次读文件会出现这个异常
		} 
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
		}finally{
				try {
					if(in!=null)
					in.close();
				} catch (IOException e) {
				}
		}
		
	}
	@Override
	public void add(User user) throws Exception {
		for(User u:userList){
			if(u.getName().equals(user.getName())){
				throw new Exception("姓名重复，不能重复注册");
			}
		}
		userList.add(user);
		save();
	}

	@Override
	public String list() {
		return userList.toString();
	}

	@Override
	public void save() {
		//读取文件中的user信息，并添加到userList中
		ObjectOutputStream out = null;
		try {
			out  = new ObjectOutputStream(new FileOutputStream(file));
			
			for(User user:userList){
				out.writeObject(user);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}finally{
				try {
					if(out!=null)
						out.close();
				} catch (IOException e) {
				}
		}
	}
	public boolean checkUser(String name,String pwd){
		for(User u:userList){
			if(u.getName().equals(name)&&u.getPwd().equals(pwd))
				return true;
		}
		return false;
	}
	@Override
	public boolean checkAdmin(String pwd) {
		
		return pwd.equals("admin");
	}
	@Override
	public User findUserByName(String name) {
		for(User user:userList){
			if(user.getName().equals(name)){
				return user;
			}
		}
		return null;
	}
}

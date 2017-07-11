package org.lanqiao.prj.feiq.util;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 一些IO工具
 * @author zheng last modified:2012-7-25
 *
 */
public class IOUtils {
	
	public static<T> void writeObjToFile(File f,T...objs) throws Exception{
		writeObjToFile(f, Arrays.asList(objs));
	}
	public static<T> void writeObjToFile(File f,List<T> objs) throws Exception{
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream(new FileOutputStream(f));
			for(T t:objs)
				out.writeObject(t);
		}catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 从文件中读取对象列表
	 * @param <T>
	 * @param file
	 * @return
	 * @throws Exception  文件不存在，或者其中保存并非对象，读写错误都会抛出异常
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> readObjFromFile(File file) throws Exception{
		ObjectInputStream in = null;
		try {
			List<T> list = new ArrayList<T>();
			in=new ObjectInputStream(new FileInputStream(file));
			while (true) {
				try {
					T g = (T) in.readObject();
					list.add(g);
				} catch (EOFException e) {//读到末尾
					break;
				} 
			}
			return list;
		}catch (Exception e) {
			throw e;
		}finally{
			if(null!=in){
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		
	}
}

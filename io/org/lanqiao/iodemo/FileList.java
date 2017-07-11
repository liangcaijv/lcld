package org.lanqiao.iodemo;

import java.io.File;
import java.io.FileFilter;
/**
 * 
 * @author zhengwei
 *
 */
public class FileList {
	public static void main(String[] args) {
		File f = new File("C:\\Program Files");
		String[] fNameList = f.list();
		for(String fName:fNameList){
			System.out.println(fName);
		}
		System.out.println("-----------------");
		
		File[] fList = f.listFiles();//可以获得更多信息
		for(File f1:fList){
			System.out.println(f1.getName());
		}
		System.out.println("------------------");
		class MyFileFilter implements FileFilter{
			@Override
			public boolean accept(File pathname) {
				if(pathname.getName().startsWith("C"))
					return true;
				return false;
			}
		}
		//过滤
		File[] fileList = f.listFiles(new MyFileFilter());
		
		
		for(File f1:fileList){
			System.out.println(f1.getName());
		}
		System.out.println("------------------"); 
	}
}

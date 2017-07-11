package org.lanqiao.iodemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * 在目录下所有文件中检索目标目标字符串
 * @author zhengwei last modified:2012-3-28
 *
 */
public class Search {
	public static void main(String[] args) {
		File f = new File("F:\\bluedot\\产品1.0");
		Search t = new Search();
		t.addSub(f);
		System.out.println(t.list.size());
		for(File f1:t.list){
			BufferedReader reader =null;
			try {
				reader = new BufferedReader(new FileReader(f1));
				String s = "";
				while((s=reader.readLine())!=null){
					if(s.contains("北大青鸟")){
						System.out.println(f1.getAbsolutePath());
						break;
					}						
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
					try {
						if(null!=reader)
							reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
	}

	List<File> list = new ArrayList<File>();
/**
 * 将f下的所有文件加入到list中
 * @param f
 */
	void addSub(File f) {
		File[] files = f.listFiles(new Filter());
		for(File f1:files){
			if(f1.isFile())
				list.add(f1);
			else
				addSub(f1);
		}
	}

	class Filter implements FileFilter {
		@Override
		public boolean accept(File pathname) {
			return true;
			/*if(pathname.isDirectory())
				return true;
			String fName = pathname.getName();
			if (fName.endsWith(".txt") || fName.endsWith(".java")
					|| fName.endsWith(".jsp") || fName.endsWith(".html")
					|| fName.endsWith(".js"))
				return true;
			return false;*/
		}
	}
	
	
}

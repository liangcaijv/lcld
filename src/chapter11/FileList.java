package chapter11;

import java.io.File;
import java.util.Arrays;

public class FileList {
	public static void main(String[] args) {
		File catalog = new File("c:");
		File[] files = catalog.listFiles();
		for (File file2 : files) {
			if(file2.isDirectory()){
				//文件夹
				System.out.println("目录类型："+file2.getName() +"\t 内附文件：" + Arrays.toString(file2.list()));
			}else if(file2.isFile()){
				//文件
				System.out.println("标准文件："+file2.getName() +"\t 文件大小：" + file2.length());
			}
		}
		
	}
}

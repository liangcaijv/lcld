package org.lanqiao.iodemo;

import java.io.*;

public class FileCopy {
	/**
	 * 将f1拷贝到f2
	 * @param f1
	 * @param f2
	 * @throws IOException 
	 */
	void copy(File f1, File f2) throws IOException {
		// 声明FileReader和FileWriter的对象
		FileReader reader = null;
		FileWriter writer = null;
		// 用第一个参数作为FileReader的构造参数，用第二个参数作为FileWriter的构造参数，分别对他们进行实例化
		try {
			reader = new FileReader(f1);
			writer = new FileWriter(f2);
			// 循环读取，同时写
			char[] arr = new char[1024];
			int num = 0; 
			while(true){
				num = reader.read(arr);
				if(num==-1){
					break;
				}else{
					String s = new String(arr,0,num);
					writer.write(s);
				}
			}
		} catch (IOException e) {
			throw e;
		} finally {
			// 关闭IO
			if(null!=reader)
				reader.close();
			if(null!=writer)
				writer.close();
		}


	}
}

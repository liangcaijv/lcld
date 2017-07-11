package org.lanqiao.iodemo;

import java.io.File;

/**
 * 找某目录名下的所有文件名
 * @author zhengwei
 *
 */
public class T1 {

	void pNames(File root,String fileName){
		File[] f1 = root.listFiles();
		for(File f2:f1){
			File f3 = new File(f2, fileName);
			File[] f4 = f3.listFiles();
			for(File f5:f4)
				System.out.println(f5.getName());
		}
	}
	public static void main(String[] args) {
		new T1().pNames(new File("F:\\bluedot\\产品1.0\\高级课程\\spring"), "平台视频");
	}
}


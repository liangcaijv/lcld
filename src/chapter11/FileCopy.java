package chapter11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {

	public static void main(String[] args) {
		
		FileCopy fileCopy = new FileCopy();
		fileCopy.copy("d:/workspace/info.txt","d:/info_copy.txt");
			
	}

	/**
	 * 文件复制
	 * @param originalPath 原始文件路径
	 * @param copyPath     目标文件路径
	 */
	public void copy(String originalPath,String copyPath){
		
		//声明待复制文件
		File file = new File("d:/workspace/info.txt");
		
		//声明复制目标文件
		File copyFile = new File("d:/info_copy.txt");
		
		//try catch 结构外部声明引用，为的是可以在finally中利用此引用关闭流
		FileInputStream fileInputStream = null;
		
		FileOutputStream fileOutputStream = null;
		
		try {
			
			//根据待复制文件创建文件输入流
			fileInputStream = new FileInputStream(file);

			//声明复制目标文件输出流
			fileOutputStream = new FileOutputStream(copyFile);
			
			//文件复制过程代码
			//获取待复制文件大小
			long fileSize = file.length();
			
			//存放文件内容的byte数组
			byte[] content = new byte[(int) fileSize];
			
			//读取文件内容到字节数组
//			//fileInputStream.read(content);//read方法有IOException需要处理
//			System.out.println(fileInputStream.read(content));
//			
//			//写入字节数组到目标文件
//			fileOutputStream.write(content);//write方法有IOException需要处理
			
			//分段多次复制法
			byte[] buffer = new byte[1024];//声明一个1024长度的数组，每次读写都在这个数组上操作
			int readLength = 0;			 //用于记录每次读取到buffer中实际内容的长度
			while(( readLength = fileInputStream.read(buffer)) != -1){
				//如果read方法返回-1,，则代表流已经读取完毕。
				
				//由于最后一次read的buffer不一定把buffer读满，所以使用能够选取写入的write方法
				fileOutputStream.write(buffer, 0, readLength);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//使用后一定要注意关闭流！！！
			try {
				//利用close()方法关闭流，close时还有一个checked异常要处理
				if(fileInputStream != null){
					fileInputStream.close();
				}
				if(fileOutputStream!=null){
					fileOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

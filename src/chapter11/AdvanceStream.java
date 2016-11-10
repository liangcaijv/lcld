package chapter11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdvanceStream {

	/**
	 * 为了代码清晰，直接把异常throws，常规需要在捕获处理并在finally中关闭流
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
//		//声明文件对象
//		File file = new File("d:/info_copy.txt");
//		
//		//利用文件对象创建文件字符输入流
//		FileReader fileReader = new FileReader(file);
//		
//		//循环输入
//		while (fileReader.ready()) {
//			System.out.print((char)fileReader.read());
//		}
//		fileReader.close();
		
		
	}
	
	public void readFile(String filePath) throws IOException{
		File file = new File(filePath);
		
		FileReader fileReader = new FileReader(file);
		
		//利用FileReader创建BufferedReader实例，
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while(bufferedReader.ready()){
			String line = bufferedReader.readLine();
			System.out.println(line);
			//解析内容
		}
		
		bufferedReader.close();
	}
	
}

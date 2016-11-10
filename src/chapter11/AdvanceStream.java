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
	 * Ϊ�˴���������ֱ�Ӱ��쳣throws��������Ҫ�ڲ�������finally�йر���
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
//		//�����ļ�����
//		File file = new File("d:/info_copy.txt");
//		
//		//�����ļ����󴴽��ļ��ַ�������
//		FileReader fileReader = new FileReader(file);
//		
//		//ѭ������
//		while (fileReader.ready()) {
//			System.out.print((char)fileReader.read());
//		}
//		fileReader.close();
		
		
	}
	
	public void readFile(String filePath) throws IOException{
		File file = new File(filePath);
		
		FileReader fileReader = new FileReader(file);
		
		//����FileReader����BufferedReaderʵ����
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while(bufferedReader.ready()){
			String line = bufferedReader.readLine();
			System.out.println(line);
			//��������
		}
		
		bufferedReader.close();
	}
	
}

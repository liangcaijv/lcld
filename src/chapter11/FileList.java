package chapter11;

import java.io.File;
import java.util.Arrays;

public class FileList {
	public static void main(String[] args) {
		File catalog = new File("c:");
		File[] files = catalog.listFiles();
		for (File file2 : files) {
			if(file2.isDirectory()){
				//�ļ���
				System.out.println("Ŀ¼���ͣ�"+file2.getName() +"\t �ڸ��ļ���" + Arrays.toString(file2.list()));
			}else if(file2.isFile()){
				//�ļ�
				System.out.println("��׼�ļ���"+file2.getName() +"\t �ļ���С��" + file2.length());
			}
		}
		
	}
}

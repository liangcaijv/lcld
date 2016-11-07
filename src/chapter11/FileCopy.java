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
	 * �ļ�����
	 * @param originalPath ԭʼ�ļ�·��
	 * @param copyPath     Ŀ���ļ�·��
	 */
	public void copy(String originalPath,String copyPath){
		
		//�����������ļ�
		File file = new File("d:/workspace/info.txt");
		
		//��������Ŀ���ļ�
		File copyFile = new File("d:/info_copy.txt");
		
		//try catch �ṹ�ⲿ�������ã�Ϊ���ǿ�����finally�����ô����ùر���
		FileInputStream fileInputStream = null;
		
		FileOutputStream fileOutputStream = null;
		
		try {
			
			//���ݴ������ļ������ļ�������
			fileInputStream = new FileInputStream(file);

			//��������Ŀ���ļ������
			fileOutputStream = new FileOutputStream(copyFile);
			
			//�ļ����ƹ��̴���
			//��ȡ�������ļ���С
			long fileSize = file.length();
			
			//����ļ����ݵ�byte����
			byte[] content = new byte[(int) fileSize];
			
			//��ȡ�ļ����ݵ��ֽ�����
//			//fileInputStream.read(content);//read������IOException��Ҫ����
//			System.out.println(fileInputStream.read(content));
//			
//			//д���ֽ����鵽Ŀ���ļ�
//			fileOutputStream.write(content);//write������IOException��Ҫ����
			
			//�ֶζ�θ��Ʒ�
			byte[] buffer = new byte[1024];//����һ��1024���ȵ����飬ÿ�ζ�д������������ϲ���
			int readLength = 0;			 //���ڼ�¼ÿ�ζ�ȡ��buffer��ʵ�����ݵĳ���
			while(( readLength = fileInputStream.read(buffer)) != -1){
				//���read��������-1,����������Ѿ���ȡ��ϡ�
				
				//�������һ��read��buffer��һ����buffer����������ʹ���ܹ�ѡȡд���write����
				fileOutputStream.write(buffer, 0, readLength);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//ʹ�ú�һ��Ҫע��ر���������
			try {
				//����close()�����ر�����closeʱ����һ��checked�쳣Ҫ����
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

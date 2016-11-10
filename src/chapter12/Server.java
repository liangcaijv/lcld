package chapter12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	/**
	 * ����������
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		//�������������󣬹��췽������Ϊ��ǰ����˿ں�
		ServerSocket serverSocket = new ServerSocket(9999);
		//�����������accept()������ʹ��������ڵȴ�״̬���ȴ��ͻ��˵�����
		Socket socket= serverSocket.accept();
		
		//�����������пͻ�������ʱ�����´���Ż�ִ�У�socket�а����˿ͻ��˷�������Ϣ��������������IP���˿ڵȵ�
		System.out.println(socket.getRemoteSocketAddress());//�����ߵ�ip���˿�
		
		
		//���ֽ�������ʽ����ȡ��������
		OutputStream outputStream = socket.getOutputStream();//������Ϣ�������
		
		InputStream inputStream = socket.getInputStream();//������Ϣ��������
		
		//��������Ϣ
		
		//��װ�ֽ�����ʹ����ǿ���ַ���������ݴ��䵽�ͻ���
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		
		//���л�����ַ���
		BufferedWriter writer = new BufferedWriter(outputStreamWriter);
		
		//����ֱ��д���ַ���
		writer.write("���ѳɹ������������");
		
		//д�뻻�з� 
		writer.newLine();//��ͬ��writer.write('\n');
		writer.flush();//ˢ�������������ǲ������ϵ������Ĺرգ�flush()ǿ��ˢ���������Ϣ��Ŀ��
		
		//��װ�ֽ�����ʹ����ǿ�ַ�����ȡ�ͻ�����������
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		
		//���л�����ַ�������
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		//��ȡ�ͻ��������һ�����ݣ���Ҫ�ͻ����ṩ���з�(\n)�����
		System.out.println(bufferedReader.readLine());
		
		//�رշ��������ͷ���Դ
		serverSocket.close();
	}
	
	
	
}

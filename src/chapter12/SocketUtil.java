package chapter12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Socket������
 * ����ָ��Э��(���ı��ַ�����'\n'�ָ�)����/����Socket��Ϣ
 */
public class SocketUtil {

	/**
	 * ��ָ��Socket�����ı���Ϣ����'\n'��Ϊ�ָ���
	 * @param socket
	 * @param message
	 * @throws IOException
	 */
	public static void sendMsg(Socket socket,String message) throws IOException{
		
		OutputStream outputStream = socket.getOutputStream();
		
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		
		BufferedWriter writer = new BufferedWriter(outputStreamWriter);
		
		writer.write(message);
		
		writer.newLine();
		
		writer.flush();
	}
	
	/**
	 * ����ָ��Socket��������Ϣ��
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	public static String receiveMsg(Socket socket) throws IOException{
		
		InputStream inputStream = socket.getInputStream();
		
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		return bufferedReader.readLine();
	}
}

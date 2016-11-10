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

public class ServerPro {
	
	private ServerSocket serverSocket;
	
	private Socket socket;
	
	
	/**
	 * ���췽����ʼ��������󼰶˿�
	 */
	public ServerPro(int port) {
		//�������������󣬹��췽������Ϊ��ǰ����˿ں�
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("����������ʧ��");
			e.printStackTrace();
		}
	}
	
	/**
	 * ������Ϣ���ͻ���
	 * @param message String������Ϣ
	 * @throws IOException
	 */
	public void sendClientMsg(String message) throws IOException{
		
//		//��ȡ�ֽ�����������������������Ϣ
//		OutputStream outputStream = socket.getOutputStream();
//		//��װ�ֽ�����ʹ�ô��л�����ַ���
//		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
//		BufferedWriter writer = new BufferedWriter(outputStreamWriter);
//		
//		//д���ַ�����Ϣ
//		writer.write(message);
//		//���뻻�з���
//		writer.newLine();
//		//ˢ����
//		writer.flush();
		SocketUtil.sendMsg(socket, message);
	}
	
	/**
	 * ��ȡ�ͻ�����Ϣ
	 * @return
	 * @throws IOException
	 */
	public String receiveClientMsg() throws IOException{
		
//		InputStream inputStream = socket.getInputStream();
//		
//		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//		
//		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//		
//		return bufferedReader.readLine();
		return SocketUtil.receiveMsg(socket);
		
	}
	
	public void start(){
		try {
			socket = serverSocket.accept();
			sendClientMsg("���ѳɹ������������");
			String message = receiveClientMsg();
			System.out.println(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			serverSocket.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ServerPro server = new ServerPro(9999);
		server.start();
	}

}

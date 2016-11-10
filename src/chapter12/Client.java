package chapter12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	
	private Socket socket;
	
	/**
	 * ���췽�������ݸ���IP�Ͷ˿ڳ�ʼ��Socket����
	 * @param ip
	 * @param port
	 */
	public Client(String ip,int port){
		//����һ�����͵�ָ��IP�Ͷ˿ڵ���Ϣ��������������ŷ⣬������Ʊ��д�õ�ַ
		try {
			socket = new Socket(ip, port);
		} catch (IOException e) {
			System.out.println("����socketʧ��");
			e.printStackTrace();
		}
	}

	/**
	 * ������Ϣ��������
	 * @param message String������Ϣ
	 * @throws IOException
	 */
	public void sendServerMsg(String message) throws IOException{
		
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
	 * ��ȡ��������Ϣ
	 * @return
	 * @throws IOException
	 */
	public String receiveServerMsg() throws IOException{
		
//		InputStream inputStream = socket.getInputStream();
//		
//		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//		
//		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//		
//		return bufferedReader.readLine();
		return SocketUtil.receiveMsg(socket);
	}
	
	public void close(){
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", 9999);
		try {
			String loginMessage = client.receiveServerMsg();
			System.out.println(loginMessage);
			client.sendServerMsg("Hello World");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

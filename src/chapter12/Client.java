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
	 * 构造方法，根据给定IP和端口初始化Socket对象
	 * @param ip
	 * @param port
	 */
	public Client(String ip,int port){
		//创建一个发送到指定IP和端口的信息包，等于是买个信封，贴上邮票，写好地址
		try {
			socket = new Socket(ip, port);
		} catch (IOException e) {
			System.out.println("创建socket失败");
			e.printStackTrace();
		}
	}

	/**
	 * 发送消息到服务器
	 * @param message String类型消息
	 * @throws IOException
	 */
	public void sendServerMsg(String message) throws IOException{
		
//		//获取字节输出流，用以向服务端输出信息
//		OutputStream outputStream = socket.getOutputStream();
//		//包装字节流，使用带有缓冲的字符流
//		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
//		BufferedWriter writer = new BufferedWriter(outputStreamWriter);
//		
//		//写入字符串消息
//		writer.write(message);
//		//插入换行符。
//		writer.newLine();
//		//刷新流
//		writer.flush();
		SocketUtil.sendMsg(socket, message);
	}
	
	/**
	 * 获取服务器消息
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

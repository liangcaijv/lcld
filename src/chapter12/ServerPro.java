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
	 * 构造方法初始化服务对象及端口
	 */
	public ServerPro(int port) {
		//创建服务器对象，构造方法参数为当前服务端口号
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("服务器启动失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送消息到客户端
	 * @param message String类型消息
	 * @throws IOException
	 */
	public void sendClientMsg(String message) throws IOException{
		
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
	 * 获取客户端消息
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
			sendClientMsg("您已成功接入服务器！");
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

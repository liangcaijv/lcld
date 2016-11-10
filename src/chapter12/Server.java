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
	 * 启动服务器
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		//创建服务器对象，构造方法参数为当前服务端口号
		ServerSocket serverSocket = new ServerSocket(9999);
		//服务器对象的accept()方法会使服务程序处于等待状态，等待客户端的连接
		Socket socket= serverSocket.accept();
		
		//仅当监听到有客户端连接时，如下代码才会执行，socket中包含了客户端发来的消息包，包括发送者IP，端口等等
		System.out.println(socket.getRemoteSocketAddress());//发送者的ip及端口
		
		
		//以字节流的形式来获取具体内容
		OutputStream outputStream = socket.getOutputStream();//发送消息用输出流
		
		InputStream inputStream = socket.getInputStream();//接收信息用输入流
		
		//处理流信息
		
		//包装字节流，使用增强的字符流完成数据传输到客户端
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		
		//带有缓冲的字符流
		BufferedWriter writer = new BufferedWriter(outputStreamWriter);
		
		//可以直接写入字符串
		writer.write("您已成功接入服务器！");
		
		//写入换行符 
		writer.newLine();//等同于writer.write('\n');
		writer.flush();//刷新流，由于我们不会马上调用流的关闭，flush()强制刷新输出流信息到目标
		
		//包装字节流，使用增强字符流读取客户端输入内容
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		
		//带有缓冲的字符输入流
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		//读取客户端输入的一行数据，需要客户端提供换行符(\n)来标记
		System.out.println(bufferedReader.readLine());
		
		//关闭服务器并释放资源
		serverSocket.close();
	}
	
	
	
}

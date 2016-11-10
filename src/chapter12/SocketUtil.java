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
 * Socket工具类
 * 根据指定协议(纯文本字符串以'\n'分割)解析/发送Socket消息
 */
public class SocketUtil {

	/**
	 * 向指定Socket发送文本消息，以'\n'作为分割标记
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
	 * 接受指定Socket发来的消息，
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

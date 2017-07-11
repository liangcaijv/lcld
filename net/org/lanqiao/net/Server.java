package org.lanqiao.net;

import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		BufferedReader reader = null;
		BufferedReader input  = null;
		PrintWriter writer = null;
		try {
			server = new ServerSocket(8081);
			client = server.accept();
			reader = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			input = new BufferedReader(new InputStreamReader(System.in));
			writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
			while (true) {
				String s = reader.readLine();//阻塞
				if (s == null)
					break;
				else
					System.out.println("客户端"
							+ client.getInetAddress().getHostAddress() + "说:"
							+ s);
				System.out.println("回复点什么呢?");
				String inputFromConsole = input.readLine();//读控制台
				writer.println(inputFromConsole);
				writer.flush();
			}
			
		}catch (SocketException e) {
			System.out.println("客户端已经断开连接.");
		} 
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				client.close();
				server.close();
			} catch (Exception e) {
				//ignore
			}
		}
	}
}

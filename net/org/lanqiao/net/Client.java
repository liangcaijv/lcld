package org.lanqiao.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
//202.115.91.104
public class Client {
	public static void main(String[] args) {
		Socket client = null;
		PrintWriter writer = null;
		BufferedReader reader = null;
		BufferedReader reader_from_server = null;
		try {
			client = new Socket("127.0.0.1", 8081);
			writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream())) ;
			reader = new BufferedReader(new InputStreamReader(System.in));//用于接收控制台输入
			reader_from_server  = new BufferedReader(new InputStreamReader(client.getInputStream()));//用于Server输入
			while(true){
				System.out.println("你想说什么?");
				String input = reader.readLine();
				writer.println(input);
				writer.flush();
				String infoFromServer = reader_from_server.readLine();
				System.out.println("Server说:"+infoFromServer);
				
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				client.close();
				writer.close();
			} catch (Exception e) {
				//
			}
			
		}
	}
}	

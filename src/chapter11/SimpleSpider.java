package chapter11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class SimpleSpider {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.baidu.com");
		URLConnection connection = url.openConnection();
		InputStream inputStream = connection.getInputStream();
		
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		while(bufferedReader.ready()){
			System.out.println(bufferedReader.readLine());
		}
		
	}
}

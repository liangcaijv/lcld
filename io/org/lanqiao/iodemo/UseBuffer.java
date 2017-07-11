package org.lanqiao.iodemo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//使用包装类BufferedReader BufferedWriter
public class UseBuffer {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = null;
		BufferedWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader("xanadu.txt"));
			writer = new BufferedWriter(new FileWriter("characteroutput.txt"));

			String s;
			while ((s = reader.readLine()) != null) {
				writer.write(s);
				writer.flush();//如果是PrintWriter且设置为自动刷新则无需手动刷新
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (writer != null) {
				writer.close();
			}
		}
	}

}

package org.lanqiao.iodemo;

import java.io.*;

public class CopyCharacters {

  public static void main(String[] args) throws IOException {
	//声明
    FileReader reader = null;
    FileWriter writer = null;

    try {
        reader = new FileReader(args[0]);
        writer = new FileWriter(args[1]);

       /*  int c;
        while ((c = reader.read()) != -1) {//每次单独读取一个字符
          writer.write(c);//每次写一个字符
        } */
		
		char[] c = new char[1024];
		int num=0;
		while(true){
			num = reader.read(c);
			if(num==-1){
				break;
			}else{
				String s=new String(c,0,num);
				writer.write(s);
			}
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

package org.lanqiao.iodemo;

import java.io.*;


public class CopyBytes {

  public static void main(String[] args) {
	//声明在try catch外面
    FileInputStream in = null;
    FileOutputStream out = null;
    try {
        in = new FileInputStream(args[0]);//以xanadu.txt为源头
        out = new FileOutputStream(args[1]);//outagain.txt为目标
		
        /* int c;
        while ((c = in.read()) != -1) {//in.read()==-1表示读到末尾
          out.write(c);
        } */
		//以下方式可节约磁盘读写
		byte[] b = new byte[1024];
		int c=0;
		while(true){
			c = in.read(b);
			if(c==-1){
				break;
			}else{
				//String(byte[] bytes, int offset, int length, String charsetName) 
				out.write(new String(b,0,c,"utf-8").getBytes("gb2312"));
			}
		}
    }catch(FileNotFoundException e){
		System.out.println("file not exists"+e);
	}catch(IOException e){
		System.out.println("io error occours"+e);
	}finally {//必须在finally中对输入输出流进行关闭
		try{
			if (in != null) {
			  in.close();
			}
			if (out != null) {
			  out.close();
			}
		}catch(IOException e){}
    }
  }
}

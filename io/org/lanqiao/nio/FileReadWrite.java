package org.lanqiao.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 示范用nio的channel和buffer来读写文件
 * @author zhengwei
 */
public class FileReadWrite {
  public static void main(String[] args) throws IOException {
    String filePath = "E:\\workspace\\c\\hello.c";
    System.out.println( getFileContent( filePath ) );
    System.out.println("---------------");
    String content = "something new";
    appendToFile( filePath, content );
  }

  /**
   * 追加文本内容到文件的末尾
   * @param filePath 文件路径
   * @param content 要追加的内容
   * @throws IOException
   */
  private static void appendToFile(String filePath, String content) throws IOException {
    RandomAccessFile file = new RandomAccessFile( filePath, "rw" );
    file.seek( file.length() ); // 偏移指针到最后
    FileChannel fc = file.getChannel();
    byte[] bytes = content.getBytes( "utf-8" );
    //根据追加内容分配空间
    ByteBuffer wbuffer = ByteBuffer.allocate( bytes.length );
    //写入缓存
    wbuffer.put( bytes  );
    wbuffer.flip();
    //缓存写到通道
    fc.write( wbuffer );
    fc.close();
  }

  /**
   * 获取文件的文本内容
   * @param filePath 文件路径
   * @return
   * @throws IOException
   */
  private static String getFileContent(String filePath) throws IOException {
    //打开通道
    FileChannel fc = FileChannel.open( new File( filePath ).toPath() );
    //缓冲区
    ByteBuffer buffer = ByteBuffer.allocate( 1024 );
    int bytesRead = fc.read( buffer );
    StringBuilder sb = new StringBuilder();
    while (bytesRead != -1) {
      buffer.flip();
      //字节数组转字符串拼接
      sb.append( Charset.forName( "utf-8" ).decode( buffer ).toString() );
      buffer.clear();
      bytesRead = fc.read( buffer );
    }
    fc.close();
    return sb.toString();
  }

}

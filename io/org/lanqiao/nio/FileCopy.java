package org.lanqiao.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;

public class FileCopy {
  public static void main(String[] args) throws IOException {
    String path1 = "E:\\workspace\\c\\hello.c";
    String path2 = "E:\\workspace\\c\\hello.cc";
    copy(path1,path2);

  }

  /**
   * 从src指定的路径拷贝到dest指定的位置
   * @param src
   * @param dest
   * @throws IOException
   */
  private static void copy(String src, String dest) throws IOException {
    FileChannel srcChannel = FileChannel.open( new File( src ).toPath()) ;
    File destFile = new File( dest );
    if (!destFile.exists()){
      destFile.createNewFile();
    }
    // 务必开启写模式
    RandomAccessFile accessFile = new RandomAccessFile( destFile, "rw" );
    FileChannel destChannel = accessFile.getChannel();
    int position = 0;
    long count = srcChannel.size();
    destChannel.transferFrom( srcChannel, position, count );
    srcChannel.close();
    destChannel.close();
  }
}

package org.lanqiao.iodemo;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;

public class FileTree {
  private final Queue<FileInfo> fileInfos = new LinkedList<FileInfo>();
  private File                  root;
  private int                   level     = Integer.MAX_VALUE;

  public FileTree(File root) {
    super();
    this.root = root;
  }

  public FileTree(File root, int level) {
    super();
    this.root = root;
    this.level = level;
  }

  public FileTree init() {
    addToQueue(root);
    return this;
  }

  public void print() {
    for (FileInfo info : fileInfos) {
      for (int i = 0; i < info.level; i++) {
        System.out.print("   ");
      }
      System.out.println("├──" + info.name);
    }
  }

  private void addToQueue(File r) {
    if (r.isHidden()) {
      return ;
    }
    int _level = getLevel(r);
    //  小于等于控制层数，且不是隐藏文件，先将当前文件入队列
    if (_level <= this.level) {
      fileInfos.add(new FileInfo(_level, r.getName()));
    }

    // 还可以处理一层且是目录
    if (_level < this.level && r.isDirectory()) {
      dealWithSubFile(r);
    }

  }

  private void dealWithSubFile(File r) {
    try {
      DirectoryStream<Path> dirStream = Files.newDirectoryStream(r.toPath());
      // 遍历目录
      dirStream.forEach((path) -> {
        File _file = path.toFile();
        if (_file.isFile())
          fileInfos.add(new FileInfo(getLevel(_file), _file.getName()));
        else
          addToQueue(_file);
      });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private int getLevel(File f) {
    if (f == root)
      return 0;
    int level = 1;
    while (!f.getParentFile().equals(root)) {
      level++;
      f = f.getParentFile();
    }
    return level;
  }

  class FileInfo {
    int    level;
    String name;

    public FileInfo(int level, String name) {
      super();
      this.level = level;
      this.name = name;
    }
  }

  public static void main(String[] args) throws Exception {
    //		System.out.println(getLevel(new File("F:\\bluedot\\产品1.0"), new File(
    //				"F:\\bluedot\\产品1.0\\初级课程\\01Java起步\\课堂案例")));
    new FileTree(new File("/Users/zhengwei/workspace/JavaAllIn"))
        .init().print();
  }
}

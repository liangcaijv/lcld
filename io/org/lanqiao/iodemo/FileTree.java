package org.lanqiao.iodemo;

import java.io.File;
import java.util.LinkedHashSet;

public class FileTree {
	private LinkedHashSet<FileInfo> fileInfos = new LinkedHashSet<FileTree.FileInfo>();
	private File root;

	public FileTree(File root) throws Exception {
		super();
		this.root = root;
		addToSet(root);
	}
	public void print(){
		for(FileInfo info:fileInfos){
			for(int i=0;i<info.getLevel()*4;i++){
				System.out.print("-");
			}
			System.out.println(info.getName());
		}
	}
	private void addToSet(File r) throws Exception {
		fileInfos.add(new FileInfo(getLevel(root, r), r.getName()));
		File[] list = r.listFiles();
		for(File f:list){
			int l = getLevel(root, f);
			if(f.isFile())
				fileInfos.add(new FileInfo(l, f.getName()));
			else{
				if(l<=4)
					addToSet(f);
			}
		}
	}

	private static int getLevel(File root, File f) throws Exception {
		if (f == root)
			return 0;
		int level = 1;
		try {
			while (!f.getParentFile() .equals(root)) {
//				System.out.println(f);
				level++;
				f = f.getParentFile();
			}
		} catch (Exception e) {
			throw e;
		}
		return level;
	}

	class FileInfo {
		private int level;
		private String name;

		public FileInfo(int level, String name) {
			super();
			this.level = level;
			this.name = name;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(getLevel(new File("F:\\bluedot\\产品1.0"), new File(
//				"F:\\bluedot\\产品1.0\\初级课程\\01Java起步\\课堂案例")));
		new FileTree(new File("F:\\bluedot\\产品1.0\\中级课程")).print();
	}
}

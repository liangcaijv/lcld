package org.lanqiao.iodemo;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {
	static final String dataFile = "invoicedata";

	static final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
	static final int[] units = { 12, 8, 13, 29, 50 };
	static final String[] descs = { "Java T-shirt", "Java Mug",
			"Duke Juggling Dolls", "Java Pin", "Java Key Chain" };

	public static void main(String[] args) throws IOException {
		RandomAccessFile rf = new RandomAccessFile(new File(dataFile), "rw");

		for (int i = 0; i < prices.length; i++) {
			rf.writeDouble(prices[i]);
			rf.writeInt(units[i]);
			rf.writeUTF(descs[i]);
		}

		try {

			while (true) {
				rf.skipBytes(12);// 跳过double和int
				System.out.println(rf.readUTF());// 读会引起指针后移
			}
		} catch (EOFException e) {
		}// EOFException作为结束标志
	}
}

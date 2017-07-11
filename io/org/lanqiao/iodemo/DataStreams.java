package org.lanqiao.iodemo;

import java.io.*;
/**
1、读出的顺序必须和写入的顺序一致
2、读出如何判断结束？
**/
public class DataStreams {
	 static final String dataFile = "invoicedata";

	  static final double[] prices ={ 19.99, 9.99, 15.99, 3.99, 4.99 };
	  static final int[] units = { 12, 8, 13, 29, 50 };
	  static final String[] descs = { "Java T-shirt",
	                                  "Java Mug",
	                                  "Duke Juggling Dolls",
	                                  "Java Pin",
	                                  "Java Key Chain" };
									  
	  public static void main(String[] args) throws IOException {
		  DataOutputStream out = null;

		  try {
		    out = new DataOutputStream(new
		      BufferedOutputStream(new FileOutputStream(dataFile)));
			  
		    for (int i = 0; i < prices.length; i ++) {
		        out.writeDouble(prices[i]);
		        out.writeInt(units[i]);
		        out.writeUTF(descs[i]);
		      }
		    } finally {
		        out.close();
		    }
			
		  DataInputStream in = null;
		  double total = 0.0;
		  try {
		    in = new DataInputStream(new
		      BufferedInputStream(new FileInputStream(dataFile)));

		    double price;
		    int unit;
		    String desc;

		    try {
		        while (true) {
		          price = in.readDouble();
		          unit = in.readInt();
		          desc = in.readUTF();
		          System.out.format("You ordered %d units of %s " +
		                            "at $%.2f%n", unit, desc, price);
		          total += unit * price;//计算总价
		        }
		      } catch (EOFException e) { }//EOFException作为读取结束的标志
		      System.out.format("For a TOTAL of: $%.2f%n", total);
		    }
		    finally {
		      in.close();
		    }
		  }
		}



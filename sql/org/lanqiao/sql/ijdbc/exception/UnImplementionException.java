package org.lanqiao.sql.ijdbc.exception;
/**
 * 某些功能未实现时抛出此异常
 * @author JBoss
 *
 */
public class UnImplementionException extends RuntimeException{

	public UnImplementionException(String msg) {
		super(msg);
	}

	public UnImplementionException() {
		// TODO Auto-generated constructor stub
	}

}

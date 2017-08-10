package org.lanqiao.concurrent.sync;

public class StaleData2 {
	public Resource rsc;
	
	public static void main(String[] args) {
		
	}
}

class Resource {
	private int n;

	public Resource(int n) {
		this.n = n;
	}

	public void assertN() {
		if (n != n)
			throw new AssertionError("This statement is false.");
	}
}
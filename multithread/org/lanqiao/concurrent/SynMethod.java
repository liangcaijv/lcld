package org.lanqiao.concurrent;

public class SynMethod {
	private static class SomeClass {
		void f() {
			synchronized (this) {
				while (true) {
					System.out.println("f in SomeClass");
				}
			}
		}

		synchronized void m() {
			while (true) {
				System.out.println("m in SomeClass");
			}
		}
	}

	public static void main(String[] args) {
		final SomeClass s1 = new SomeClass();
		final SomeClass s2 = new SomeClass();
		new Thread(new Runnable() {

			@Override
			public void run() {
				s1.f();
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				s1.m();
			}
		}).start();
	}
}

package org.lanqiao.concurrent.cases.signallamp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarRegister {
	private List<Car> cars = new ArrayList<Car>();
	private Lock lock = new ReentrantLock();

	public CarRegister() {
	}

	private void startWork() {
		Thread t = new Thread() {
			@Override
			public void run() {
				super.run();
				lock.lock();
				if (cars.size() < 10) {// ����������
					cars.add(new Car(Consts.getRandomDirection(), CarRegister.this));
				}
				lock.unlock();
			}
		};
		t.start();
	}

	public void remove(Car car) {
		lock.lock();
		cars.remove(car);
		lock.unlock();
	}
}

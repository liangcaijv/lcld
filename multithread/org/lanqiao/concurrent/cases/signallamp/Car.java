package org.lanqiao.concurrent.cases.signallamp;

public class Car {
	private String direction;
	private CarRegister carRegister;
	public Car(String direction, CarRegister carRegister) {
		super();
		this.direction = direction;
		this.carRegister = carRegister;
	}
	
}

package com.company.poly;

public class SamsungTV implements TV {

	@Override
	public void turnOn() {		
		System.out.println("SamsungTV - ���� On");
	}

	@Override
	public void turnOff() {
		System.out.println("SamsungTV - ���� Off");

	}

	@Override
	public void soundUp() {
		System.out.println("SamsungTV - ���� Up");

	}

	@Override
	public void soundDown() {
		System.out.println("SamsungTV - ���� Down");

	}

}

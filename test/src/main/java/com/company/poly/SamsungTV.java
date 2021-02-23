package com.company.poly;

public class SamsungTV implements TV {

	@Override
	public void turnOn() {		
		System.out.println("SamsungTV - Àü¿ø On");
	}

	@Override
	public void turnOff() {
		System.out.println("SamsungTV - Àü¿ø Off");

	}

	@Override
	public void soundUp() {
		System.out.println("SamsungTV - º¼·ý Up");

	}

	@Override
	public void soundDown() {
		System.out.println("SamsungTV - º¼·ý Down");

	}

}

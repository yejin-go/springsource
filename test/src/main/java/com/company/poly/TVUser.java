package com.company.poly;

public class TVUser {
	public static void main(String[] args) {
		
		//TV lg = new SamsungTV();		
		//TV lg = new LgTV(new AppleSpeaker());		
		LgTV lg = new LgTV();
		lg.setSpeaker(new AppleSpeaker());
		lg.turnOn();
		lg.soundUp();
		lg.soundDown();
		lg.turnOff();		
		
//		SamsungTV samsung = new SamsungTV();
//		samsung.powerOn();
//		samsung.volumeUp();
//		samsung.volumeDown();
//		samsung.powerOff();
	}
}

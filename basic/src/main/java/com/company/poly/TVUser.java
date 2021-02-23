package com.company.poly;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		//다형성 
		
		//컨테이너 구동
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	    TV lg = (TV) ctx.getBean("samsung");
		//TV lg = new SamsungTV();		
		//TV lg = new LgTV(new AppleSpeaker());		
		
		lg.turnOn();
		lg.soundUp();
		lg.soundDown();
		lg.turnOff();
		
		TV tv = (TV) ctx.getBean("samsung");
		System.out.println(lg==tv?"같은 객체":"다른 객체");

		//		SamsungTV samsung = new SamsungTV();
//		samsung.powerOn();
//		samsung.volumeUp();
//		samsung.volumeDown();
//		samsung.powerOff();
	}
}

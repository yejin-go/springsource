package com.company.poly;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		//������ 
		
		//�����̳� ����
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
	    TV lg = (TV) ctx.getBean("samsung");
		//TV lg = new SamsungTV();		
		//TV lg = new LgTV(new AppleSpeaker());		
		
		lg.turnOn();
		lg.soundUp();
		lg.soundDown();
		lg.turnOff();
		
		TV tv = (TV) ctx.getBean("samsung");
		System.out.println(lg==tv?"���� ��ü":"�ٸ� ��ü");

		//		SamsungTV samsung = new SamsungTV();
//		samsung.powerOn();
//		samsung.volumeUp();
//		samsung.volumeDown();
//		samsung.powerOff();
	}
}

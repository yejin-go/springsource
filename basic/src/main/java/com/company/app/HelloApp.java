package com.company.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		//MessageBean msg = new MessageBean();
		System.out.println("=======�����̳� ���� ��=======");
		//������ �����̳� ����
		ApplicationContext ctx =
				new ClassPathXmlApplicationContext("config.xml");
		System.out.println("=======�����̳� ���� ��=======");
		//������ �����̳ʷκ��� �ʿ��� ��ü�� ����
		MessageBean msg = (MessageBean) ctx.getBean("msg");
		msg.sayHello("ȫ�浿");		
	}
}

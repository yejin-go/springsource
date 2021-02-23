package com.company.transaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.service.SampleService;

public class SampleMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("sample_config.xml");
		SampleService service = (SampleService) ctx.getBean("sample");
		
		String data = "Starry\r\n"+"Starry night\r\n"+"Paint your paletter blue and grey\r\n"+
		"look out on a summer's day";
		
		service.addData(data);
	}

}

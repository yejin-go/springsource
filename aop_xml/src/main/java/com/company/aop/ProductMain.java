package com.company.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductMain {

	public static void main(String[] args) throws Exception {
		log.info("ProductMain 실행");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop_config2.xml");
		Product product = (Product) ctx.getBean("product");
		product.setCompany("LEGO");
		product.setName("LEGO블럭");
		product.setPrice("250000");
		product.getInfo();
	}

}

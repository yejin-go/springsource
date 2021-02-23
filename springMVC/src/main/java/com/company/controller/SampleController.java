package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller  //@Component, @Service, @Repository 와 같음.
@RequestMapping("/sample/*")    // http://localhost:/8080/sample/~~~~~~
public class SampleController {
	
	@RequestMapping("/basic")   // http://localhost:/8080/sample/basic
	public void basic() {
		log.info("basic....");  //  /sample/basic => view 리졸버
	}
	
	@RequestMapping("/test")
	public String test() {   // http:localhost:8080/sample/test
		log.info("test....");
		return "default";   //  default => view 리졸버
	}
}

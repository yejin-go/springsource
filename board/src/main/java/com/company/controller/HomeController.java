package com.company.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("Welcome home!");	
		
		return "index";
	}
	
	@GetMapping("/except")
	public void exceptTest(int no) {
		logger.info("예외 테스트");
	}
	
	@GetMapping("/accessError")
	public String accessError(Authentication auth) {
		logger.info("접근 제한"+auth);
		return "/member/accessError";
	}
}

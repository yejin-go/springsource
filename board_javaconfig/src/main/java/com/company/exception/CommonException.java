package com.company.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice   //AOP
@Slf4j
public class CommonException {
	
	@ExceptionHandler(Exception.class)
	public String except(Exception e, Model model) {
		log.error("Exception...."+e) ;
		model.addAttribute("error", e.getMessage());
		return "except/error";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String notFound(NoHandlerFoundException e) {
		log.error("Exception");
		return "except/view404";
	}
}

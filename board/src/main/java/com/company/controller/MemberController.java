package com.company.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
	
		@GetMapping("/all")
		public void allAccess() {
			log.info("모든 사람 가능");
		}
		
		@GetMapping("/member")
		public void memberAccess() {
			log.info("멤버만 가능");
		}
		
		@GetMapping("/admin")
		public void adminAccess() {
			log.info("관리자 가능");
		}
		
		@GetMapping("/login")
		public void loginForm(String error, Model model, String logout) {
			log.info("로그인 폼 요청 ");
			if(error != null) {
				model.addAttribute("error","로그인 정보를 확인해 주세요");
			}
		}

		//@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')") //표현식 사용가능
		@Secured({"ROLE_MEMBER","ROLE_ADMIN"})                    //표현식 사용 불가능 is Authenticated
		@GetMapping("/doMember")
		public void doMember() {
			log.info("doMember 호출");
		}
		
		//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
		@Secured("ROLE_ADMIN")
		@GetMapping("/doAdmin")
		public void doAdmin() {
			log.info("doAdmin 호출");
		}
}

package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.AuthVO;
import com.company.domain.ChangeVO;
import com.company.domain.LoginVO;
import com.company.domain.RegisterVO;
import com.company.service.RegisterService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j //log4j2 와 동일
@RequestMapping("/member/*")
public class RegisterController {
	
	@Autowired
	private RegisterService service;
	
	@GetMapping("/step1")
	public void step1Get() {     // http://localhost:8080/member/step1
		log.info("step1 페이지 요청");
	}
	
	// step2 보여주는 컨트롤러 생성
	@PostMapping("/step2")     //  http://localhost:8080/member/step2
	public String step2Get(boolean agree, RedirectAttributes rttr) {
		log.info("step2 회원가입 페이지 요청"+agree);
		
		//사용자가 체크한 값이 없다면 step1으로 돌려보내기
		if(!agree) {
			rttr.addFlashAttribute("check", "false");
			return "redirect:/member/step1";
		}
		return "/member/step2";
	}
	@PostMapping("/step3")
	public String step3Post(@ModelAttribute("regist")RegisterVO regist) {
		//step2.jsp에서 넘긴 값 가져오기
		//로그출력
		log.info("회원가입 요청"+regist);
		// step3로 이동(두 개의 비밀번호가 일치한다면)
		if(regist.isPasswordEqualToConfirmPassword()) {
			service.register(regist);
			return "/member/step3";
		} else {
			// 만약 두개의 비밀번호가 일치하지 않는다면 다시 step2로 돌려보내기
			return "/member/step2";
		}
	}
	
	// http://localhost:8080/member/step2 또는 step3 를 Get으로 요청하는 핸들러
	@GetMapping(value= {"/step2", "/step3"})
	public String handleStep2_3() {
		log.info("/step, /step3 직접 요청");
		return "redirect:step1";
	}
	
	//중복 아이디 확인
	               // WEB-INF/views/false.jsp
	@ResponseBody  //리턴값은 실제 값이라는 의미.
	@PostMapping("/checkId")
	public String checkId(String userid) {
		log.info("중복 아이디 검사 요청.." +userid);
		RegisterVO dupId=service.selectById(userid);
		if(dupId!=null) {
			return "false";
		}
		return "true";
	}
	
	//로그인 - sign in 보여주기
	@GetMapping("/signin")
	public void login() {
		log.info("로그인 페이지 요청...");
	}
	
	//로그인 정보(아이디, 비밀번호)를 가져오는 컨트롤러
	@PostMapping("/signin")
	public String loginPost(LoginVO login, HttpSession session) {
		log.info("로그인 페이지 요청..."+login);
		AuthVO auth=service.isLogin(login);
		if(auth!=null) {
			session.setAttribute("auth", auth);
			return "redirect:/";
		}else { //userid 또는 password가 틀려서 로그인을 못하는 경우
			return "redirect:signin";
		}
	}
	
	
	//로그아웃 - 세션해제 후 index로 이동
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("로그아웃 요청...");
		session.invalidate(); //세션에 있는 정보 모두 삭제
		// session.removeAttribute("auth"); //특정 세션만 삭제
		return "redirect:/";
	}
	
	//회원탈퇴 폼 보여주기
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("회원탈퇴 봄 보여주기");
	}
	
	//회원탈퇴 - 회원삭제하고 세션해제 후 index로 이동
	@PostMapping("/leave")
	public String leavePost(LoginVO login, HttpSession session) {
		log.info("회원탈퇴 요청 " +login);
		
		if(service.leave(login)) {
			//authVO : userid, name
			session.invalidate();
			return "redirect:/";
		}else { //비밀번호가 틀린 경우
			return "redirect:leave";
		}
	}
	
	//회원정보수정 폼 보여주기
	@GetMapping("/changePwd")
	public void changeInfo() {
		log.info("회원 정보 수정 폼 요청");
	}
	
	@PostMapping("/changePwd") //localhost:8080/member/changePwd + post
	public String changePost(ChangeVO change, @SessionAttribute AuthVO auth, HttpSession session, RedirectAttributes rttr) {
		//회원정보 수정 - change(password, new_password, confirm_password)
		log.info("회원정보 수정 " +change);
		//userid 세션에서 가져와서 change에 담기
		  //AuthVO auth = (AuthVO) session.getAttribute("auth"); 
		//윗 줄 대신 메서드 안에 AuthVO auth 선언하고 앞에 @SessionAttribute 그 앞에 붙여줌.
		change.setUserid(auth.getUserid());
		
		//service에 비밀번호 변경 요청		
		//성공 => 세션해제 후 로그인 페이지로 이동
		if (service.update(change)) {
			session.invalidate();
			return "redirect:signin";
		} else { //실패 => 비밀번호 변경 폼 보여주기 이동
			rttr.addFlashAttribute("error", "비밀번호를 확인해 주세요.");
			return "redirect:changePwd";
		}
	}
}

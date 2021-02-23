package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.ChangeVO;
import com.company.domain.RegisterVO;
import com.company.service.RegisterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RegisterController {
	
	@Autowired
	private RegisterService service;
	
	@PostMapping("/regist")
	public ResponseEntity<String> regist(RegisterVO regist) {
		log.info("회원가입 요청");
		return service.register(regist)?
				new ResponseEntity<String>("success", HttpStatus.OK):
				new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/changePwd")
	public ResponseEntity<String> change(@RequestBody ChangeVO vo) {
		log.info("회원정보 수정 요청"+vo);
		vo.setUserid("kim12345");
		return service.update(vo)?
				new ResponseEntity<String>("success", HttpStatus.OK):
				new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}

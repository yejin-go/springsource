package com.company.service;

import com.company.domain.AuthVO;
import com.company.domain.ChangeVO;
import com.company.domain.LoginVO;
import com.company.domain.RegisterVO;

public interface RegisterService {
	//아이디 중복
	public RegisterVO selectById(String userid);
	//회원가입
	public boolean register(RegisterVO regist);
	//로그인 => 로그인 성공시 userid, name
	public AuthVO isLogin(LoginVO login);
	//회원탈퇴
	public boolean leave(LoginVO login);
	//회원비밀번호 변경
	public boolean update(ChangeVO change);
}

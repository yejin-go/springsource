package com.company.mapper;

import com.company.domain.AuthVO;
import com.company.domain.ChangeVO;
import com.company.domain.LoginVO;
import com.company.domain.RegisterVO;

public interface RegisterMapper {
	public RegisterVO selectById(String userid);
	public int insert(RegisterVO regist);
	public AuthVO selectByMember(LoginVO login);
	public int leaveMember(LoginVO login);
	public int updateMember(ChangeVO change);
}

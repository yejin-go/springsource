package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.MemberVO;
import com.company.persistence.MemberDAO;

@Service("service")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	
	@Override
	public boolean memberInsert(MemberVO member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean memberUpdate(MemberVO member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean memberDelete(MemberVO member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MemberVO getMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> getMemberList() {
		
		return dao.getList();
	}

}

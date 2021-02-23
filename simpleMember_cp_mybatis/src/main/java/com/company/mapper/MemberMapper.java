package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.MemberVO;
public interface MemberMapper {
	public int insert(MemberVO vo);
	public int update(MemberVO vo);
	public int delete(MemberVO member);
	public MemberVO select(MemberVO member);
	public List<MemberVO> selectAll();	
}

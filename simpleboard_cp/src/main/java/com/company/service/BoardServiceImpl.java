package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardVO;
import com.company.persistence.BoardDAO;

@Service("service") //빈(객체)을 생성하겠다. 의미
public class BoardServiceImpl implements BoardService {
	
	@Autowired  // 이미 생성된 빈을 주입하는 역할
	private BoardDAO dao;
//	
//	public BoardServiceImpl() {
//		dao = new BoardDAO();
//	}
	
	@Override
	public int insertBoard(BoardVO vo) {
		
		return dao.insert(vo);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardVO getRow(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}

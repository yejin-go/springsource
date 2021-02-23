package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookVO;
import com.company.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper mapper;
	
	@Override
	public boolean insertBook(BookVO book) {
		// TODO Auto-generated method stub
		return mapper.insert(book)>0?true:false;
	}

	@Override
	public boolean removeBook(int code) {
		// TODO Auto-generated method stub
		return mapper.delete(code)>0?true:false;
	}

	@Override
	public boolean updateBook(int code, int price) {
		// TODO Auto-generated method stub
		return mapper.update(code, price)>0?true:false;
	}

	@Override
	public List<BookVO> getList() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}

	@Override
	public List<BookVO> searchList(String criteria, String keyword) {
		// TODO Auto-generated method stub
		return mapper.search(criteria, keyword);
	}

}

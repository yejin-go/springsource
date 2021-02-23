package com.company.service;

import java.util.List;

import com.company.domain.BookVO;

public interface BookService {
	//도서정보입력
	public boolean insertBook(BookVO book);
	//도서정보삭제
	public boolean removeBook(int code);
	//도서정보수정
	public boolean updateBook(int code, int price);
	//도서정보전체조회
	public List<BookVO> getList();
	//도서정보특정조회
	public List<BookVO> searchList(String criteria, String keyword);
}

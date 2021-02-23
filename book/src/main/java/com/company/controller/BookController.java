package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BookVO;
import com.company.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookController {
	
	@Autowired
	private BookService service;
	
	@PostMapping("/insert")
	public String insert(BookVO book, RedirectAttributes rttr) {
		log.info("도서 정보 입력 요청" +book);
		try {		
			if(service.insertBook(book)) {
				return "redirect:/select";
			} else {
				return "redirect:/";
			} 
		}catch (Exception e) {
				rttr.addFlashAttribute("tab", "insert");
				return "redirect:/";
		}	
	}	
	
	//전체 리스트
	@GetMapping("/select")
	public String selectAll(Model model) {
		log.info("전체 리스트 가져오기");
		//서비스 작업 호출
		List<BookVO> list = service.getList();
		model.addAttribute("list", list);
		
		return "book_selectAll";
	}
	
	//도서정보 삭제
	@PostMapping("/delete")
	public String deletePost(int code, RedirectAttributes rttr) {
		log.info("도서 정보 삭제 "+code);
		//성공하면 리스트 보여주기
		if(service.removeBook(code)) {
			return "redirect:/select";
		}else {		//실패하면 index 보여주기
			rttr.addFlashAttribute("tab", "delete");
			return "redirect:/";
		}
	}
	
	//도서정보 수정
	@PostMapping("/update")
	public String updatePost(int code, int price, RedirectAttributes rttr) {
		log.info("도서 정보 수정 " +code+" : "+price);
		//성공하면 리스트 보여주기
		if(service.updateBook(code, price)) {
			return "redirect:/select";
		}else { //실패하면 index 보여주기
			rttr.addFlashAttribute("tab", "modify");
			return "redirect:/";
		}
	}
	
	//도서 정보 검색
	@PostMapping("/search")
	public String search(String criteria, String keyword, Model model, RedirectAttributes rttr) {
		log.info("도서 정보 검색 " +criteria+ ", "+keyword);
		//성공하면 book_searchAll
		List<BookVO> list=service.searchList(criteria, keyword); 
		if(!list.isEmpty()) {  //list.size() > 0
			model.addAttribute("list", list);
			return "book_searchAll";
		}else { //실패하면 index
			rttr.addFlashAttribute("tab", "search");
			return "redirect:/";
		}
	}
	@GetMapping("/search")
	public String searchGet(RedirectAttributes rttr) {
		log.info("search Form 요청");
		rttr.addFlashAttribute("tab", "search");
		return "redirect:/";
	}
}
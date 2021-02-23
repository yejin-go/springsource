package com.company.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.FileAttach;
import com.company.domain.PageVO;
import com.company.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	//게시글 작성 폼 보여주기
	@GetMapping("/register")
	public void register() {
		log.info("게시글 등록 폼 보여주기");
	}
	
	//게시글 작성
	@PostMapping("/register")
	public String registerPost(BoardVO board, RedirectAttributes rttr) {
		log.info("게시글 등록 " +board);
		
		//파일첨부 확인
//		if(board.getAttachList()!=null) {
//			board.getAttachList().forEach(attach -> log.info(""+attach));
//		}
		
		log.info("게시글 번호 "+board.getBno());
		if(service.regist(board)) {
			// 등록성공 메시지를 모달로 띄우기 위해 조금 전 등록된 글 번호 보내기
			rttr.addFlashAttribute("result", board.getBno());
			return "redirect:/board/list";
		}
		return "register";
	}
	
	//게시글 목록 보기
	@GetMapping("/list")
	public void list(Model model, Criteria cri) {
		log.info("게시글 보기");
		//전체목록 요청
		List<BoardVO> list = service.getList(cri);
		//전체게시물 수 요청
		int total = service.getTotalCnt(cri);
		model.addAttribute("list", list);
		model.addAttribute("pageVO", new PageVO(cri,total));
	}
	
	//사용자가 선택한 게시물 보기
	 //   /board/read?bno=61 
	 //   /board/modify?bno=61  
	@GetMapping({"/read","/modify"})  //   /board/read
	public void get(int bno, @ModelAttribute("cri")Criteria cri, Model model) {
		log.info("특정 게시물 보기 " +bno);
		log.info("Criteria" +cri);
		BoardVO board = service.getRow(bno);
		model.addAttribute("board", board);  // /board/read, /board/modify
	} 
	
	//특정 게시물 삭제
	@PostMapping("/remove")
	public String remove(int bno, Criteria cri, RedirectAttributes rttr) {
		log.info("게시물 삭제 " +bno);
		
		//게시물 번호에 해당하는 첨부 파일 삭제(서버 폴더 파일 삭제, 데이터베이스도 삭제)
		
		//서버 폴더 안 파일 삭제하기
		//1.bno에 해당하는 첨부물 목록 알아내기
		List<FileAttach> attachList = service.getAttachList(bno);
		//성공하면 리스트 보여주기
		if(service.remove(bno)) { //2.데이터베이스 삭제(게시물, 첨부물)
			//3.파일 삭제
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:list";		
	}
	
	private void deleteFiles(List<FileAttach> attachList) {
		log.info("첨부물 삭제"+attachList);
		
		if(attachList == null || attachList.size() <=0 ) {
			return;
		}
		
		for(FileAttach attach:attachList) {
			Path path = Paths.get("d:\\upload\\", attach.getUploadPath()+"\\",
						attach.getUuid()+"_", attach.getFileName());
			
			//일반파일, 이미지 원본 파일 삭제
			try {
			Files.deleteIfExists(path);
			
			if(Files.probeContentType(path).startsWith("image")) {
				Path thumb = Paths.get("d:\\upload\\", attach.getUploadPath()+"\\s_",
								 attach.getUuid()+"_", attach.getFileName());
				Files.delete(thumb);
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//특정 게시물 수정
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("게시물 수정 "+board);
		log.info("criteria - "+cri);
		
		//파일 첨부 확인
		if (board.getAttachList()!=null) {
			board.getAttachList().forEach(attach -> log.info(""+attach));
		}
		
		service.modify(board);
		
		rttr.addFlashAttribute("result", "success");
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:list";
	}
	
	//첨부물 가져오기
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileAttach>> getAttachList(int bno){
		log.info("첨부물 가져오기 : "+bno);
		return new ResponseEntity<List<FileAttach>>(service.getAttachList(bno),HttpStatus.OK);
	}
	
}
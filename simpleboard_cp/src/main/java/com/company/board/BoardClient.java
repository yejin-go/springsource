package com.company.board;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BoardVO;
import com.company.service.BoardService;

public class BoardClient {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("board_config.xml");
		//서비스 호출
		BoardService service = (BoardService) ctx.getBean("service");		
		BoardVO vo = new BoardVO();
		vo.setTitle("스프링 컨테이너");
		vo.setContent("스프링 주요 개념 - DI");
		vo.setWriter("홍길동");
		int result = service.insertBoard(vo);
		if (result>0)
			System.out.println("삽입 성공");
	}

}

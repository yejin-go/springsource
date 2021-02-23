package com.company.board;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BoardVO;
import com.company.service.BoardService;

public class BoardClient {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("board_config.xml");
		//���� ȣ��
		BoardService service = (BoardService) ctx.getBean("service");		
		BoardVO vo = new BoardVO();
		vo.setTitle("������ �����̳�");
		vo.setContent("������ �ֿ� ���� - DI");
		vo.setWriter("ȫ�浿");
		int result = service.insertBoard(vo);
		if (result>0)
			System.out.println("���� ����");
	}

}

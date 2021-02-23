package com.company.book;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BookVO;
import com.company.service.BookService;

public class BookClient {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("book_config.xml");
		BookService service = (BookService) ctx.getBean("service");
		
		//���� ���� �Է�
		BookVO insertVO = new BookVO(1004, "�ڹٽ�ũ��Ʈ", "���ο�", 28000);
		if(service.insertBook(insertVO)) {
			System.out.println("���� �Է� ����");
		}
		
		//��ü ����Ʈ ��������
		List<BookVO> list = service.getList();
		for(BookVO vo: list) {
			System.out.println(vo);
		}
	}
}

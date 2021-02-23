package com.company.student;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentMain{

	public static void StudentMain(String[] args) {
		//StudentInfo info = new StudentInfo(new Student("ȫ�浿","15","1","4"));
		//info.getStudentInfo();
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config2.xml");
		StudentInfo info = (StudentInfo) ctx.getBean("");
		info.getStudentInfo();
	}

}

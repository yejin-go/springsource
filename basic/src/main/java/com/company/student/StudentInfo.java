package com.company.student;

public class StudentInfo {
	private Student student;
	
	//�ʱ�ȭ => ������, ���� ����
	
	public void getStudentInfo() {
		if(student!=null) {
			System.out.println("�̸� : "+student.getName());
			System.out.println("���� : "+student.getAge());
			System.out.println("�г� : "+student.getGradeName());
			System.out.println("�� : "+student.getClassNum());
		}
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentInfo(Student student) {
		super();
		this.student = student;
	}
}

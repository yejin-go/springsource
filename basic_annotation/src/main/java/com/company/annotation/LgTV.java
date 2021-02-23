package com.company.annotation;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV {
	
	//@Component => ��ü ���� => Ȱ��ȭ ��Ű�� �ڵ� : <context:component-scan package=""/> 
	// ������Ʈ ��ĵ ��Ű���� ������̼� ���Ǳװ� �ϴ� �ϱ��� �� ����. 
	//@Autowired �Ǵ� @Inject �� �� �ϳ��� ����ؾ� ��. ���� ���
	//@Qualifire : �̸� ������
	//��ü ���� + �̸� ���� �Ѳ����� : @Resourse : java9���� ������. 
	//=> annotation Ȱ��ȭ �����ִ� �ڵ� �ʿ���.
	// : <context:annotation-config/>:@Autowired,@inject,@qualifier Ȱ��ȭ.
	
	//@Autowired //������ ������ ������ ��ü ����
	@Inject //������ ������ ������ ��ü ����
	@Qualifier("apple") //�ܵ� ��� �Ұ�(���� ����� �������� ��� Ư�� ��ü�� �����ϴµ� ���)
	private Speaker speaker;
	
	
//	private int price;
//	
//	public void setSpeaker(Speaker speaker) {
//		this.speaker = speaker;
//	}
//	public void setPrice(int price) {
//		this.price = price;
//	}
	
//	public LgTV() {
//		speaker = new SonySpeaker();
//	}
	
//	public LgTV(Speaker speaker) {
//		this.speaker = speaker;
//	}
	
	@Override
	public void turnOn() {
		System.out.println("LGTV - ���� On");
	}
	@Override
	public void turnOff() {
		System.out.println("LGTV - ���� Off");
	}
	@Override
	public void soundUp() {
		//System.out.println("LGTV - ���� Up");
		speaker.volumeUp();
	}
	@Override
	public void soundDown() {
		//System.out.println("LGTV - ���� Down");
		speaker.volumeDown();
	}
}

package com.company.annotation;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV {
	
	//@Component => 객체 생성 => 활성화 시키는 코드 : <context:component-scan package=""/> 
	// 컴포넌트 스캔 패키지는 어노테이션 컨피그가 하는 일까지 다 해줌. 
	//@Autowired 또는 @Inject 둘 중 하나만 사용해야 함. 같은 기능
	//@Qualifire : 이름 구별용
	//객체 주입 + 이름 구별 한꺼번에 : @Resourse : java9에서 삭제됨. 
	//=> annotation 활성화 시켜주는 코드 필요함.
	// : <context:annotation-config/>:@Autowired,@inject,@qualifier 활성화.
	
	//@Autowired //적절한 시점에 생성된 객체 주입
	@Inject //적절한 시점에 생성된 객체 주입
	@Qualifier("apple") //단독 사용 불가(주입 대상이 여러개인 경우 특정 객체를 지정하는데 사용)
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
		System.out.println("LGTV - 전원 On");
	}
	@Override
	public void turnOff() {
		System.out.println("LGTV - 전원 Off");
	}
	@Override
	public void soundUp() {
		//System.out.println("LGTV - 볼륨 Up");
		speaker.volumeUp();
	}
	@Override
	public void soundDown() {
		//System.out.println("LGTV - 볼륨 Down");
		speaker.volumeDown();
	}
}

package com.company.poly;

public class LgTV implements TV {
	
	private Speaker speaker;

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
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

package com.company.poly;

public class LgTV implements TV {
	
	private Speaker speaker;
	private int price;
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
//	public LgTV() {
//		speaker = new SonySpeaker();
//	}
	
//	public LgTV(Speaker speaker) {
//		this.speaker = speaker;
//	}
	
	@Override
	public void turnOn() {
		System.out.println("LGTV - Àü¿ø On, °¡°Ý: " +price);
	}
	@Override
	public void turnOff() {
		System.out.println("LGTV - Àü¿ø Off");
	}
	@Override
	public void soundUp() {
		//System.out.println("LGTV - º¼·ý Up");
		speaker.volumeUp();
	}
	@Override
	public void soundDown() {
		//System.out.println("LGTV - º¼·ý Down");
		speaker.volumeDown();
	}
}

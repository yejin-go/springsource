package com.company.poly;

public class SonySpeaker implements Speaker {
	public SonySpeaker() {
		System.out.println("=======SonySpeaker��ü ����");
		
	}
	@Override
	public void volumeUp() {
		System.out.println("=======SonySpeaker Volume Up");

	}
	@Override
	public void volumeDown() {
		System.out.println("=======SonySpeaker Volume Down");
    }
}
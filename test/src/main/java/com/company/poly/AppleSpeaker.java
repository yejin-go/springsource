package com.company.poly;

public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("=========AppleSpeaker °´Ã¼ »ý¼º");
	}
	@Override
	public void volumeUp() {
		System.out.println("===============AppleSpeaker Volume Up");

	}

	@Override
	public void volumeDown() {
		System.out.println("===============AppleSpeaker Volume Down");

	}

}

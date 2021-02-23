package com.company.annotation;

import org.springframework.stereotype.Component;

@Component("sony")
public class SonySpeaker implements Speaker {
	public SonySpeaker() {
		System.out.println("=======SonySpeaker°´Ã¼ »ý¼º");
		
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
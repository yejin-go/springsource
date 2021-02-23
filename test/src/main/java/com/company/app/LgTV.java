package com.company.app;

import com.company.poly.SonySpeaker;
import com.company.poly.Speaker;

public class LgTV {
	public void turnOn() {
		System.out.println("LGTV - ���� On");
	}
	public void turnOff() {
		System.out.println("LGTV - ���� Off");
	}
	public void soundUp() {
		//System.out.println("LGTV - ���� Up");
		Speaker speaker = new SonySpeaker();
		speaker.volumeUp();
	}
	public void soundDown() {
		//System.out.println("LGTV - ���� Down");
		Speaker speaker = new SonySpeaker();
		speaker.volumeDown();
	}
}

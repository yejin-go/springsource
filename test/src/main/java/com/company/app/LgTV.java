package com.company.app;

import com.company.poly.SonySpeaker;
import com.company.poly.Speaker;

public class LgTV {
	public void turnOn() {
		System.out.println("LGTV - Àü¿ø On");
	}
	public void turnOff() {
		System.out.println("LGTV - Àü¿ø Off");
	}
	public void soundUp() {
		//System.out.println("LGTV - º¼·ý Up");
		Speaker speaker = new SonySpeaker();
		speaker.volumeUp();
	}
	public void soundDown() {
		//System.out.println("LGTV - º¼·ý Down");
		Speaker speaker = new SonySpeaker();
		speaker.volumeDown();
	}
}

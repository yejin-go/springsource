package com.company.bank;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import org.junit.Test;

public class CellPhoneMmsSenderTest {
	final String MESSAGE = "테스트 문자 메시지";
	
	@Test
	public void testSend() {
		CellPhoneService mock = mock(CellPhoneService.class);
		CellPhoneMmsSender sender = new CellPhoneMmsSender(mock);
		
		sender.send(MESSAGE);
		verify(mock).sendMMS(MESSAGE);
	}
}

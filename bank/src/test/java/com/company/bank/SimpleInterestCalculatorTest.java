package com.company.bank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SimpleInterestCalculatorTest {
	private InterestCalculator interestCal;
	
	@Before
	public void init() {
		interestCal = new SimpleInterestCalculator();
		interestCal.setRate(0.05);
	}
	
	@Test
	public void calculator() { //정상으로 수행되는 경우
		double interest = interestCal.calculator(10000, 2);
		//첫번째 : 기대값, 두번째: 실제 메소드 실행 후 리턴값, 세번째: 허용오차값
		assertEquals(interest, 10000.0,0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illegalCalculate() {  //위에 작성한 예외가 던져지길 기대하는 테스트 메소드
		interestCal.calculator(-10000, 2);
	}
}

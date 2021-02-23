package com.company.bank;

public interface InterestCalculator {
	void setRate(double rate);
	double calculator(double amount, double year);
}

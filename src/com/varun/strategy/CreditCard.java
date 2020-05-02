package com.varun.strategy;

public class CreditCard implements PaymentStrategy {

	@Override
	public void pay(float amount) {
		System.out.println(amount + " paid using CC");
	}
}

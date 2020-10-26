package com.varun.behavioural.strategy;

public class CreditCard implements PaymentStrategy {

	@Override
	public void pay(float amount) {
		System.out.println(amount + " paid using CC");
	}
}

package com.varun.behavioural.strategy;

public class Paypal implements PaymentStrategy {

	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Paypal(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	@Override
	public void pay(float amount) {
		System.out.println(
				amount + " Paid using Paypal credentails" + " Username:" + userName + " and Password: " + password);
	}

}

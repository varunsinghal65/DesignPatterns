package com.varun.strategy;

/**
 * Type : 
 * Behavioural (it's used to manage algorithms, relationships and responsibilities between objects.)
 * 
 * Other names :
 * Policy pattern
 * 
 * Purpose:
 * Defines a set of encapsulated algorithms that can be swapped to carry out a specific behaviour
 * 
 * Examples in JDK :
 * Collections.sort() takes in a comparator as a param, this comparator decides the sorting algo/logic at RT
 * 
 * Source URL :
 * https://www.journaldev.com/1754/strategy-design-pattern-in-java-example-tutorial
 * 
 * @author Varrox
 *
 */
public class PaymentStrategyClient {

	public static void main(String[] args) {
		performPayment(new CreditCard());
		performPayment(new Paypal("varun", "pass"));
	}
	
	private static void performPayment(PaymentStrategy ps) {
		ps.pay(10000);
	}
	
}

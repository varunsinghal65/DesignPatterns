package com.varun.singleton;

/**
 * 
 * Approach 2 :
 * 
 * "Synchronized" keyword ensures that only one thread can access
 * getInstance(),while other threads wait for their turn, this leads to a
 * performance overhead in the multi threaded environment
 * 
 * @author Varrox
 *
 */
public class Singleton2 {

	private static Singleton2 instance = null;

	private Singleton2() {
		System.out.println("Constructor called");
	}

	public static synchronized Singleton2 getInstance() {
		if (instance == null) {
			instance = new Singleton2();
			return instance;
		} else {
			return instance;
		}
	}
	public static void main(String[] args) {
		System.out.println(getInstance());
		System.out.println(getInstance());
		System.out.println(getInstance());
	}
}



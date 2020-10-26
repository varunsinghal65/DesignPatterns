package com.varun.creational.singleton;

/**
 * Singleton Design pattern :
 * 
 * Only one instance of the class should be created
 * 
 * Approach #1 :
 * 
 * - lazy instantiate,however, in a multithreaded environment, 2 threads enter getInstance()
 * simultaneously, 2 objects will be created :-(
 * 
 * @author Varrox
 *
 */

public class Singleton1 {
	
	/**
	 * Preventing external instantiations
	 */
	private Singleton1() {
		System.out.println("Constructor called");
	}
	
	private static Singleton1 instance = null;
	
	public static Singleton1 getInstance() {
		if (instance == null) {
			instance = new Singleton1();
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


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

 class SingletonFirst {
	
	/**
	 * Preventing external instantiations
	 */
	private SingletonFirst() {
		System.out.println("Constructor called");
	}
	
	private static SingletonFirst instance = null;
	
	public static SingletonFirst getInstance() {
		if (instance == null) {
			instance = new SingletonFirst();
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


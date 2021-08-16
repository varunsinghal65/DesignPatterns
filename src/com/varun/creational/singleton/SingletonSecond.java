package com.varun.creational.singleton;

/**
 * 
 * Approach 2 :
 * 
 * "Synchronized" keyword ensures that only one thread can access
 * getInstance(),while other threads wait for their turn, this leads to a
 * performance overhead in the multi threaded environment
 *
 * https://stackoverflow.com/questions/21812396/what-is-the-use-of-static-synchronized-method-in-java/21812514#:~:text=In%20simple%20words%20a%20static,the%20method%20at%20a%20time.
 *
 *
 * @author Varrox
 *
 */
public class SingletonSecond {

	private static SingletonSecond instance = null;

	private SingletonSecond() {
		System.out.println("Constructor called");
	}

	public static synchronized SingletonSecond getInstance() {
		if (instance == null) {
			instance = new SingletonSecond();
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



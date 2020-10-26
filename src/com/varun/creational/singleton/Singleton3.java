package com.varun.creational.singleton;

/**
 * Approach #3 :
 * 
 * We will not synchronize a whole method but a part of it. We will implement
 * "double checking locking" mechanism This will ensure that synchronized block
 * is executed only ONCE, no matter how many threads access the method
 * concurrently In Approach #2, synchronized method was executed by every
 * thread.
 * 
 * @author Varrox
 *
 */

public class Singleton3 {

	private static Singleton3 instance = null;

	private Singleton3() {
		System.out.println("Constructor called");
	}

	public static Singleton3 getInstance() {
		/**
		 * null check 1, will prevent access to syncronized block (this block is source
		 * of performance degradation)after 1st object has been created of Singelton3.
		 * 
		 * So, this ensures that sync block exec happens only once, since it is expensive.
		 */
		if (instance == null) {
			/**
			 * this is the lock
			 */
			synchronized (Singleton3.class) {
				/**
				 * Null check 2, let's say when the app started, 3 threads entered here at the
				 * same time JVM will determine the fastest, and allow it to enter and block
				 * other 2. Once thread 1 is done, when other threads will try to enter, they
				 * will not be allowed, since instance is no more null
				 */
				if (instance == null) {
					instance = new Singleton3();
				}
			}
		}
		return instance;
	}
	
	public static void main(String[] args) {
		System.out.println(getInstance());
		System.out.println(getInstance());
		System.out.println(getInstance());
	}
}

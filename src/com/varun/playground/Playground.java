package com.varun.playground;

public class Playground {

	private static Playground instance;
	
	private Playground() {
	}
	
	public static Playground getInstance() {
		if (instance == null) {
			synchronized(Playground.class) {
				if (instance == null) {
					instance = new Playground();
				}
			}
		}
		return instance;
	} 
}

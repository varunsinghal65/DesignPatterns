package com.varun.factory;

public abstract class ShapeFactory {

	public static Shape getShapeInstance(ShapeTypes sType) {
		Shape instance = null;
		
		switch(sType) {
		/**
		 * Below, if i use ShapeTypes.SQUARE, compile time error is thrown because in a switch construct, javac
		 * infers from the switch statement variable(sType) the type of the constants, which are specified in 
		 * case.
		 * 
		 * Read here more, answer by user "Kru" : https://stackoverflow.com/questions/10161408/java-using-switch-statement-with-enum-under-subclass
		 * 
		 * How sType and SQUARE are different, sType is a variable, SQUARE is a constant ?
		 * 
		 * switch (x) {}, X should always be a variable, in our case this variable holds a constant
		 * 
		 */
		case SQUARE : 
			instance = new Square();
			break;
		case CIRCLE : 
			instance = new Circle();
			break;
		case RECTANGLE : 
			instance = new Rectangle();
			break;
		default: System.out.println("no match found");	
		}
		return instance;
	}
}

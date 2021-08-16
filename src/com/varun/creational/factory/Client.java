package com.varun.creational.factory;
/**
 * 
 * factory design pattern (https://dzone.com/articles/factory-method-design-pattern)
 * 
 * It is used when
 * 
 * 1. when logic for creation of objects is to be hidden/ decoupled from client (SRP,
 * complex objects, separation of concerns)
 * 2. you have a base class and want to instantiate child classes based on input configuration
 * 3. you want to code to the interfaces/abstract classes instead of implementation.
 * 			--> enforces O in S"O"LID
 * 
 * @author Varrox
 *
 */
public class Client {
	public static void main(String[] args) {
		Shape shapeObject = ShapeFactory.getShapeInstance(ShapeTypes.CIRCLE);
		shapeObject.draw();
		
		shapeObject = ShapeFactory.getShapeInstance(ShapeTypes.RECTANGLE);
		shapeObject.draw();
		
		shapeObject = ShapeFactory.getShapeInstance(ShapeTypes.SQUARE);
		shapeObject.draw();
	}
}

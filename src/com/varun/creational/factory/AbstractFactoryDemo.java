package com.varun.creational.factory;

public class AbstractFactoryDemo {

    /**
     * Use abstract factory desing pattern
     *  -- to avoid conditional logic if else OR switch
     *  -- if object creation needs good amount of complex logic
     * https://www.digitalocean.com/community/tutorials/abstract-factory-design-pattern-in-java
     * https://en.wikipedia.org/wiki/Abstract_factory_pattern#/media/File:Abstract_factory.svg
     * @param args
     */
    public static void main(String[] args) {
        AbstractShapeFactory factory = new RectangleFactory();
        Shape shape = factory.createShape();
        shape.draw();
    }

}

interface AbstractShapeFactory {

    Shape createShape();

}

class RectangleFactory implements AbstractShapeFactory {

    @Override
    public Shape createShape() {
        return new Rectangle();
    }
}

class CircleFactory implements AbstractShapeFactory {

    @Override
    public Shape createShape() {
        return new Circle();
    }
}

class SquareFactory implements AbstractShapeFactory {
    @Override
    public Shape createShape() {
        return new Square();
    }
}
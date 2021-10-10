package com.krymlov.shape;

public class Circle extends Shape{

    private double radius;

    public double getRadius() {
        return radius;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getSquare() {
        return Math.PI*Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*radius;
    }
}

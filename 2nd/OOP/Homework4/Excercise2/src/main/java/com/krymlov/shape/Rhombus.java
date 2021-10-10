package com.krymlov.shape;

public class Rhombus extends Shape{

    double h, a;

    public Rhombus(double a, double h) {
        this.a = a;
        this.h = h;
    }

    @Override
    public double getSquare() {
        return a*h;
    }

    @Override
    public double getPerimeter() {
        return 4*a;
    }
}

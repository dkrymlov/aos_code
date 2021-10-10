package com.krymlov.shape;

public class Triangle extends Shape{

    double a, b, c;
    double p;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getSquare() {
        this.p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    @Override
    public double getPerimeter() {
        return a+b+c;
    }
}

package com.krymlov.univ.triangles;

public class RightTriangle extends ATriangle{

    public RightTriangle(double a, double b, double angle) {
        super(a, b, angle);
        this.c = Math.sqrt(a*a+b*b);
    }

    @Override
    public void calcSquare() {
        System.out.println("Площа: " + 0.5*a*b);
    }

    @Override
    public void calcPerimeter() {
        System.out.println("Периметр: " + (a+b+c));
    }
}

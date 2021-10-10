package com.krymlov.univ.triangles;

public class IsoscelesTriangle extends ATriangle{

    public IsoscelesTriangle(double a, double b, double angle) {
        super(a, b, angle);
        this.c = 2*a*Math.sin(Math.toRadians(angle/2));//основа рівнобедреного трикутника
    }

    @Override
    public void calcSquare() {
        double p = (a+b+c)/2;
        System.out.println("Площа: " + Math.sqrt(p*(p-a)*(p-b)*(p-c)));
    }

    @Override
    public void calcPerimeter() {
        System.out.println("Периметр: " + (a+b+c));
    }
}

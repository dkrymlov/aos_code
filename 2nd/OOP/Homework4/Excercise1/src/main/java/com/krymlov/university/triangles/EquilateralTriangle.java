package com.krymlov.university.triangles;

public class EquilateralTriangle extends Triangle{
    private double sqare;

    public EquilateralTriangle(double a) {
        super(a);
    }

    public void getAngles(){
        System.out.println("Every angle is 60 degree");
    }

    public void calcSquare() {
        double p = (a+a+a)/2;
        this.sqare = Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    public double getSqare() {
        return sqare;
    }

    public double getPerimeter() {
        return a*3;
    }
}

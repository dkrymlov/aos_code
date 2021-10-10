package com.krymlov.univ.triangles;

public abstract class ATriangle {

    protected double a, b, c;
    protected double angle;

    public ATriangle(double a, double b, double angle) {
        this.a = a;
        this.b = b;
        this.c = 0;
        if (angle > 360 || angle < 0){
            throw new IllegalArgumentException("Число от 0 до 360");
        }else this.angle = angle;
    }

    public abstract void calcSquare();
    public abstract void calcPerimeter();
}

package com.krymlov.excerises.sixth.adapter;

public class SquareStick {

    private double width;
    public double radius;

    public SquareStick(double width){
        this.width = width;
        radius = width/Math.sqrt(2);
    }

    public double getRadius(){
        return this.radius;
    };

}

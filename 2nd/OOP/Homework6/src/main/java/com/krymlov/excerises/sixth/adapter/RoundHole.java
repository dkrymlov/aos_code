package com.krymlov.excerises.sixth.adapter;

public class RoundHole {
    private double radius;

    public RoundHole(double radius){
        this.radius = radius;
    }

    public double getRadius(){
        return radius;
    }

    public boolean isSuitable(RoundStick roundStick){
        if (this.radius >= roundStick.getRadius()){
            return true;
        }else return false;
    }

    public boolean isSuitable(SquareStick squareStick){
        if (this.radius >= squareStick.getRadius()){
            return true;
        }else return false;
    }


}

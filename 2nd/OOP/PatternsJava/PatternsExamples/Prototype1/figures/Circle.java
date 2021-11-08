package com.krymlov.excerises.fifth.figures;

public class Circle implements IFigure {

    int radius;

    public Circle(int radius){
        this.radius = radius;
    }

    @Override
    public IFigure clone() {
        return new Circle(this.radius);
    }

    @Override
    public void getInfo() {
        System.out.println("Коло з радіусом " + this.radius);
    }
}

package com.krymlov.excerises.fifth.figures;

public class Rectangle implements IFigure {

    int width;
    int height;

    public Rectangle(int width, int height){
        this.width = width;
        this.height = height;
    }
    @Override
    public IFigure clone() {
        return new Rectangle(this.width, this.height);
    }

    @Override
    public void getInfo() {
        System.out.println("Прямокутник з висотою " + this.height + " та шириною " + this.width);
    }
}

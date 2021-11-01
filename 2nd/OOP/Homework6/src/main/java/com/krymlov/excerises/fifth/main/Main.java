package com.krymlov.excerises.fifth.main;

import com.krymlov.excerises.fifth.figures.Circle;
import com.krymlov.excerises.fifth.figures.IFigure;
import com.krymlov.excerises.fifth.figures.Rectangle;
import com.krymlov.excerises.fifth.figures.Triangle;

public class Main {
    public static void main(String[] args) {
        IFigure figure = new Rectangle(10, 20);
        IFigure clonedFigure = figure.clone();
        figure.getInfo();
        clonedFigure.getInfo();
        figure = new Circle(15);
        clonedFigure = figure.clone();
        figure.getInfo();
        clonedFigure.getInfo();
        figure = new Triangle(5,7,10);
        clonedFigure = figure.clone();
        figure.getInfo();
        clonedFigure.getInfo();
    }
}

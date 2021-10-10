package com.krymlov.main;

import com.krymlov.shape.*;

public class Main {
    public static void main(String[] args) {
        String per = "Периметр: ";
        String sqr = "Площа: ";
        {
            int radius = 4;
            Shape circle = new Circle(radius);
            System.out.println("Для кола з радіусом " + radius + ":");
            System.out.println(per + circle.getPerimeter());
            System.out.println(sqr + circle.getSquare());
        }
        {
            double a = 3;
            double b = 5;
            Shape rect = new Rectangle(a, b);
            System.out.println("Для прямокутника зі сторонами " + a + " " + b + ":");
            System.out.println(per + rect.getPerimeter());
            System.out.println(sqr + rect.getSquare());
        }
        {
            double a = 7;
            double h = 5;
            Shape rhombus = new Rhombus(a, h);
            System.out.println("Для ромба зі стороною і висотою " + a + " " + h + ":");
            System.out.println(per + rhombus.getPerimeter());
            System.out.println(sqr + rhombus.getSquare());
        }
        {
            double a = 10;
            Shape square = new Square(10);
            System.out.println("Для квадрата зі стороною " + a + ":");
            System.out.println(per + square.getPerimeter());
            System.out.println(sqr + square.getSquare());
        }
        {
            double a = 5;
            double b = 4;
            double c = 2;
            Shape triangle = new Triangle(5, 4, 2);
            System.out.println("Для трикутника зі сторонами " + a + " " + b + " " + c + ":");
            System.out.println(per + triangle.getPerimeter());
            System.out.println(sqr + triangle.getSquare());
        }
    }
}

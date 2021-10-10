package com.krymlov.university.triangles;

public class Main {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(3, 4, 6);
        System.out.println("Периметр трикутника зі сторонами 3 4 6: " + triangle.getPerimeter());
        EquilateralTriangle triangle1 = new EquilateralTriangle(5);
        System.out.println("Периметр рівностороннього трикутника зі стороною 5: " + triangle1.getPerimeter());
        triangle1.calcSquare();
        System.out.println("Площа рівностороннього трикутника зі стороною 5: " + triangle1.getSqare());
    }
}

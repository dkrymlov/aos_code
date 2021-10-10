package com.krymlov.univ.triangles;

public class Main {
    public static void main(String[] args) {
        ATriangle triangle1 = new IsoscelesTriangle(5.0,5.0,60.0);
        triangle1.calcPerimeter();
        triangle1.calcSquare();
        ATriangle triangle2 = new RightTriangle(5, 7, 90);
        triangle2.calcPerimeter();
        triangle2.calcSquare();
    }
}

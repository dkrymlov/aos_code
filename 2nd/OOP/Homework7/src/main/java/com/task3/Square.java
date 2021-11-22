package com.task3;

public class Square {
    private int side;

    Square(int side) {
        this.side = side;
    }

    void setSide(int side) {
        this.side = side;
    }

    int getSide() {
        return side;
    }

    public int getSquareArea() {
        return side * side;
    }
}

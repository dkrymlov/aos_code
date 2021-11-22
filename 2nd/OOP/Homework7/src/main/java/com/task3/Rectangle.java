package com.task3;

public class Rectangle {
    public int width;
    public int height;

    Rectangle(int w, int h) {
        width = w;
        height = h;
    }

    void setHeight (int h) {
        height = h;
    }
    void setWidth (int w) {
        width = w;
    }

    int getHeight () {
        return height;
    }
    int getWidth () {
        return width;
    }

    public int getRectangleArea() {
        return width * height;
    }
}

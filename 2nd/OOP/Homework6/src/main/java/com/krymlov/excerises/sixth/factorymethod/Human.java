package com.krymlov.excerises.sixth.factorymethod;

import java.awt.*;

public class Human {

    enum SkinColor{
        BLACK, WHITE
    }

    private int age;
    private SkinColor skinColor;
    boolean isAlive;
    Color eyeColor;

    private Human(int age, SkinColor skinColor, boolean isAlive, Color eyeColor ){
        this.age = age;
        this.skinColor = skinColor;
        this.isAlive = isAlive;
        this.eyeColor = eyeColor;
    }

    public void getHumanInfo(){
        System.out.println(this.age);
        System.out.println(this.skinColor);
        System.out.println(this.isAlive);
        System.out.println(this.eyeColor);
    }

    public static Human getHuman(int age, SkinColor skinColor, boolean isAlive){
        //we can change some vars inside the factorymethod or add smth new
        Color color = Color.BLUE;
        return new Human(age, skinColor, isAlive, color);
    }
}

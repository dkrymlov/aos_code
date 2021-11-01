package com.krymlov.excerises.sixth.adapter;

public class Main {
    public static void main(String[] args) {
        //create a roundhole and try to suit in 2 types of sticks
        //sqarestick is seems uncompatible anyway, but it also has a radius of square over the sqare
        RoundHole roundHole = new RoundHole(3);
        RoundStick roundStick = new RoundStick(3);
        SquareStick squareStick = new SquareStick(5);
        System.out.println(roundHole.isSuitable(roundStick));
        System.out.println(roundHole.isSuitable(squareStick));
    }
}

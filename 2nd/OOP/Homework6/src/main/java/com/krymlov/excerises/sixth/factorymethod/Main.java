package com.krymlov.excerises.sixth.factorymethod;

public class Main {
    public static void main(String[] args) {
        Human human = Human.getHuman(15, Human.SkinColor.BLACK, true);
        human.getHumanInfo();
    }
}

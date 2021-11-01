package com.krymlov.excerises.third.builder.pizza;

public class Pizza {
        String dough;
        String sauce;
        String topping;
        public Pizza(){ }
        public void setDough(String d) { dough = d; }
        public void setSauce(String s) { sauce = s; }
        public void setTopping(String t) { topping = t; }
        public void info() {
            System.out.println("Dough: " + dough);
            System.out.println("Sauce: " + sauce);
            System.out.println("Topping: " + topping);
        }
    }

package com.krymlov.excerises.third.builder.pizzabuilders;

import com.krymlov.excerises.third.builder.pizzabuilders.PizzaBuilder;

public class SpicyPizzaBuilder extends PizzaBuilder {
    @Override
    public void buildDough() {
        pizza.setDough("panbaked");
    }

    @Override
    public void buildSauce() {
        pizza.setSauce("hot");
    }

    @Override
    public void buildTopping() {
        pizza.setTopping("Pepparoni+salami");
    }
}

package com.krymlov.excerises.third.builder.pizzabuilders;

import com.krymlov.excerises.third.builder.pizzabuilders.PizzaBuilder;

public class HawaiianPizzaBuilder extends PizzaBuilder {
    @Override
    public void buildDough() {
        pizza.setDough("cross");
    }

    @Override
    public void buildSauce() {
        pizza.setSauce("mild");
    }

    @Override
    public void buildTopping() {
        pizza.setTopping("Ham + pineapple");
    }
}

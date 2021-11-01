package com.krymlov.excerises.third.builder.main;

import com.krymlov.excerises.third.builder.pizza.Pizza;
import com.krymlov.excerises.third.builder.waiter.Waiter;
import com.krymlov.excerises.third.builder.pizzabuilders.HawaiianPizzaBuilder;
import com.krymlov.excerises.third.builder.pizzabuilders.PizzaBuilder;
import com.krymlov.excerises.third.builder.pizzabuilders.SpicyPizzaBuilder;

public class Main {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        PizzaBuilder hawaiianPizzaBuilder = new HawaiianPizzaBuilder();
        PizzaBuilder spicyPizzaBuilder = new SpicyPizzaBuilder();

        waiter.setPizzaBuilder(hawaiianPizzaBuilder);
        waiter.constructPizza();

        Pizza pizza = waiter.getPizza();
        pizza.info();
    }
}

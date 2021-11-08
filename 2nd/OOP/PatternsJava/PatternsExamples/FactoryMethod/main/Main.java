package com.krymlov.excerises.sixth.factorymethod.main;

import com.krymlov.excerises.sixth.factorymethod.creators.ConcreteCreator;
import com.krymlov.excerises.sixth.factorymethod.creators.Creator;
import com.krymlov.excerises.sixth.factorymethod.product.Product;

public class Main {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        for (int i = 1; i < 3; i++) {
            Product product = creator.getInstance(i);
            System.out.println(product.getType());
        }
    }
}

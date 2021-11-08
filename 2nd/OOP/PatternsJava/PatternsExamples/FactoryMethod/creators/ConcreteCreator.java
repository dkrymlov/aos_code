package com.krymlov.excerises.sixth.factorymethod.creators;

import com.krymlov.excerises.sixth.factorymethod.product.ConcreteProduct1;
import com.krymlov.excerises.sixth.factorymethod.product.ConcreteProduct2;
import com.krymlov.excerises.sixth.factorymethod.product.Product;

public class ConcreteCreator extends Creator {
    @Override
    public Product getInstance(int type) {
        if (type == 1){
            return new ConcreteProduct1();
        }else if (type == 2){
            return new ConcreteProduct2();
        }else return null;
    }
}

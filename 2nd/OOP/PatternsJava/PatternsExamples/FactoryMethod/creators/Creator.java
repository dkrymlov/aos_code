package com.krymlov.excerises.sixth.factorymethod.creators;

import com.krymlov.excerises.sixth.factorymethod.product.Product;

public abstract class Creator {
    public abstract Product getInstance(int type);
}

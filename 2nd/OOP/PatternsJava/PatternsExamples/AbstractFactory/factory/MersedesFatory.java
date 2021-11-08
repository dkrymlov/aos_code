package com.krymlov.excerises.third.absFactory.factory;

import com.krymlov.excerises.third.absFactory.cars.Car;
import com.krymlov.excerises.third.absFactory.cars.Mersedes;
import com.krymlov.excerises.third.absFactory.engines.Engine;
import com.krymlov.excerises.third.absFactory.engines.MersedesEngine;
import com.krymlov.excerises.third.absFactory.interfaces.ICarFactory;

public class MersedesFatory implements ICarFactory {
    @Override
    public Car createCar() {
        return new Mersedes();
    }

    @Override
    public Engine createEngine() {
        return new MersedesEngine();
    }
}

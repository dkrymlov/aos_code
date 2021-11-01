package com.krymlov.excerises.third.absFactory.factory;

import com.krymlov.excerises.third.absFactory.cars.Car;
import com.krymlov.excerises.third.absFactory.cars.Toyota;
import com.krymlov.excerises.third.absFactory.engines.Engine;
import com.krymlov.excerises.third.absFactory.engines.ToyotaEngine;
import com.krymlov.excerises.third.absFactory.interfaces.ICarFactory;

public class ToyotaFactory implements ICarFactory {
    @Override
    public Car createCar() {
        return new Toyota();
    }

    @Override
    public Engine createEngine() {
        return new ToyotaEngine();
    }
}

package com.krymlov.excerises.third.absFactory.interfaces;

import com.krymlov.excerises.third.absFactory.cars.Car;
import com.krymlov.excerises.third.absFactory.engines.Engine;

public interface ICarFactory {
    Car createCar();
    Engine createEngine();
}

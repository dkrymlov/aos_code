package com.krymlov.excerises.third.absFactory.main;

import com.krymlov.excerises.third.absFactory.cars.Car;
import com.krymlov.excerises.third.absFactory.engines.Engine;
import com.krymlov.excerises.third.absFactory.engines.MersedesEngine;
import com.krymlov.excerises.third.absFactory.engines.ToyotaEngine;
import com.krymlov.excerises.third.absFactory.factory.MersedesFatory;
import com.krymlov.excerises.third.absFactory.factory.ToyotaFactory;
import com.krymlov.excerises.third.absFactory.interfaces.ICarFactory;

public class Main {

    public static void main(String[] args) {
        ICarFactory carFactory = new ToyotaFactory();
        Car mycar = carFactory.createCar();
        mycar.getInfo();
        Engine myengine = carFactory.createEngine();
        myengine.getPower();

        ICarFactory carFactory2 = new MersedesFatory();
        Car mycar2 = carFactory2.createCar();
        mycar2.getInfo();
        Engine myengine2 = carFactory2.createEngine();
        myengine2.getPower();
    }




}

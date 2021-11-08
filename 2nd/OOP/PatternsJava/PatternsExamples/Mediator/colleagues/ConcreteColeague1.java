package com.krymlov.excerises.third.mediator.colleagues;

import com.krymlov.excerises.third.mediator.mediators.Mediator;

import java.io.Console;

public class ConcreteColeague1 extends Colleague {

    public ConcreteColeague1(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        mediator.Send(message, this);
    }

    public void notify(String message) {
        System.out.println("Colleague1 gets message: " + message);
    }

}

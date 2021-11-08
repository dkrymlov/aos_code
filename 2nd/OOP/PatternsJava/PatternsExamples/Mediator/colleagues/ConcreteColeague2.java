package com.krymlov.excerises.third.mediator.colleagues;

import com.krymlov.excerises.third.mediator.mediators.Mediator;

public class ConcreteColeague2 extends Colleague{

    public ConcreteColeague2(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        mediator.Send(message, this);
    }

    public void notify(String message) {
        System.out.println("Colleague2 gets message: " + message);
    }

}

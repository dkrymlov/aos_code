package com.krymlov.excerises.third.mediator.mediators;

import com.krymlov.excerises.third.mediator.colleagues.Colleague;

public abstract class Mediator {
    public abstract void Send(String message, Colleague colleague);
}

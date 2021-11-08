package com.krymlov.excerises.third.mediator.colleagues;

import com.krymlov.excerises.third.mediator.mediators.Mediator;

public abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator)
    {
        this.mediator = mediator;
    }

}

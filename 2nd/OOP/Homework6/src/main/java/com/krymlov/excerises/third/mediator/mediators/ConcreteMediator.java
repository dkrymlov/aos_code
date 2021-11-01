package com.krymlov.excerises.third.mediator.mediators;

import com.krymlov.excerises.third.mediator.colleagues.Colleague;
import com.krymlov.excerises.third.mediator.colleagues.ConcreteColeague1;
import com.krymlov.excerises.third.mediator.colleagues.ConcreteColeague2;

public class ConcreteMediator extends Mediator{

    private ConcreteColeague1 colleague1;
    private ConcreteColeague2 colleague2;
    public ConcreteColeague1 setColleague1(ConcreteColeague1 value) {
        colleague1 = value;
        return colleague1;
    }
    public ConcreteColeague2 setColleague2(ConcreteColeague2 value) {
        colleague2 = value;
        return colleague2;
    }
    @Override
    public void Send(String message, Colleague colleague) {
        if (colleague == colleague1)
        {
            colleague2.notify(message);
        }
        else
        {
            colleague1.notify(message);
        }

    }
}

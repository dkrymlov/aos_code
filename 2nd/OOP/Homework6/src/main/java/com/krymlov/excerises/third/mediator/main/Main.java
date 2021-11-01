package com.krymlov.excerises.third.mediator.main;

import com.krymlov.excerises.third.mediator.colleagues.ConcreteColeague1;
import com.krymlov.excerises.third.mediator.colleagues.ConcreteColeague2;
import com.krymlov.excerises.third.mediator.mediators.ConcreteMediator;

public class Main {
    public static void main(String[] args) {
        ConcreteMediator m = new ConcreteMediator();
        ConcreteColeague1 c1 = new ConcreteColeague1(m);
        ConcreteColeague2 c2 = new ConcreteColeague2(m);
        m.setColleague1(c1);
        m.setColleague2(c2);
        m.Send("How are you?", c1);
        m.Send("Fine, thanks", c2);
    }
}

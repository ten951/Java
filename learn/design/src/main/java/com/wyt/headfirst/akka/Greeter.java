package com.wyt.headfirst.akka;

import akka.actor.UntypedAbstractActor;
import akka.actor.UntypedActor;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/7.
 */
public class Greeter extends UntypedAbstractActor{
    public enum Msg {
        GREET, DONE
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Msg.GREET) {
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(message);
        }
    }
}

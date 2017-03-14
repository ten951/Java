package com.wyt.headfirst.akka;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedAbstractActor;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/8.
 */
public class WatchActor extends UntypedAbstractActor {
    public WatchActor(ActorRef ref) {
        getContext().watch(ref);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Terminated) {
            System.out.println(String.format("%s has terminated, shutting down system", ((Terminated) message).getActor().path()));
            getContext().system().terminate();
        }else {
            unhandled(message);
        }
    }
}

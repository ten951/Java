package com.wyt.headfirst.akka;

import akka.actor.*;
import com.typesafe.config.ConfigFactory;
import scala.Option;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/8.
 */
public class RestartActor extends UntypedAbstractActor {
    public enum Msg {
        DONE, RESTART
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Msg.DONE) {
            getContext().stop(getSelf());
        } else if (message == Msg.RESTART) {
            System.out.println(((Object) null).toString());//这里会抛出异常Nullpoint
            double a = 0 / 0;//这里会抛出异常Arithmetic
        }
        unhandled(message);
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("preStart hashcode:" + this.hashCode());
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("postStop hashcode:" + this.hashCode());
    }

    @Override
    public void preRestart(Throwable reason, Option opt) throws Exception {
        System.out.println("preRestart hashcode:" + this.hashCode());
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        System.out.println("postRestart hashcode:" + this.hashCode());

    }

}

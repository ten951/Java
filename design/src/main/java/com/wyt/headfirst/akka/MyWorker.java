package com.wyt.headfirst.akka;

import akka.actor.*;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/8.
 */
public class MyWorker extends UntypedAbstractActor {
    public enum Msg {
        WORKING, DONE, CLOSE
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("MyWorker is starting");
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("MyWorker is stopping");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Msg.WORKING) System.out.println("I am working");
        if (message == Msg.DONE) System.out.println("Stop working");
        if (message == Msg.CLOSE) {
            System.out.println("I will shutdown");
            getSender().tell(Msg.CLOSE, getSelf());
            getContext().stop(getSelf());
        } else
            unhandled(message);
    }

    public static void main(String[] args) throws TimeoutException {
        //创建一个叫deadMain的系统
        ActorSystem system = ActorSystem.create("deadMain", ConfigFactory.load("Samplehello.conf"));
        //创建一个MyWorker的经纪人 worker
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");
        //构造一个与之绑定的邮箱Inbox.
        final Inbox inbox = Inbox.create(system);
        inbox.watch(worker);//使用邮箱监视MyWorker
        //通过邮箱给MyWorker发送消息
        inbox.send(worker, Msg.WORKING);
        inbox.send(worker, Msg.DONE);
        inbox.send(worker, Msg.CLOSE);
        while (true) {
            Object msg = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
            if (msg == Msg.CLOSE) {
                System.out.println("My worker is closing");
            } else if (msg instanceof Terminated) {
                System.out.println("My worker is dead");
                system.terminate();
                break;
            } else {
                System.out.println(msg);
            }

        }

    }
}

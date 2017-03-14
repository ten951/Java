package com.wyt.headfirst.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/7.
 */
public class HelloMainSimple {


    public static void customStrategy(ActorSystem system) {
        //ActorRef相当于经纪人,创建了一个叫做"Supervisor"的actor
        ActorRef a = system.actorOf(Props.create(Supervisor.class), "Supervisor");
        //给lifecycle发送消息
        a.tell(Props.create(RestartActor.class), ActorRef.noSender());//第一个参数是消息实体 第二参数是 发送人,ActorRef.noSender()表示不关心发送人
        ActorSelection sel = system.actorSelection("akka://lifecycle/user/Supervisor/restartActor");
        for (int i = 0; i < 100; i++) {
            sel.tell(RestartActor.Msg.RESTART, ActorRef.noSender());
        }
    }

    public static void main(String[] args) {
        //创建了一个Actor系统,起名字叫做lifecycle
        ActorSystem system = ActorSystem.create("lifecycle", ConfigFactory.load("Samplehello.conf"));
        customStrategy(system);


    }



   /* public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("Samplehello.conf"));
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        System.out.println("HelloWorld Actor Path:" + a.path());
    }*/
}

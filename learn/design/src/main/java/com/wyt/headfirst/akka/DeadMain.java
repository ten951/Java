package com.wyt.headfirst.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/8.
 */
public class DeadMain {
    public static void main(String[] args) {

        //创建一个叫deadMain的系统
        ActorSystem system = ActorSystem.create("deadMain", ConfigFactory.load("Samplehello.conf"));
        //创建一个MyWorker的经纪人 worker
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");
        //创建一个WatchActor的经纪人 watcher 并监视worker
        system.actorOf(Props.create(WatchActor.class, worker), "watcher");
        //发送消息给worker working
        worker.tell(MyWorker.Msg.WORKING,ActorRef.noSender());
        //发送消息给worker done
        worker.tell(MyWorker.Msg.DONE,ActorRef.noSender());
        //发送消息给worker poisonPill 毒死接收方,让其终止
        worker.tell(PoisonPill.getInstance(),ActorRef.noSender());

    }
}

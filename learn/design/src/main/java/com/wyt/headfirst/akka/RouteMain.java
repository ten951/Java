package com.wyt.headfirst.akka;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.agent.Agent;
import com.typesafe.config.ConfigFactory;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/9.
 */
public class RouteMain {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("router", ConfigFactory.load("Samplehello.conf"));
        ActorRef w = system.actorOf(Props.create(WatchActor.class), "watcher");
        int i=1;
    }
}

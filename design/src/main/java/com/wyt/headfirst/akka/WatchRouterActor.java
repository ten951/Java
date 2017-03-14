package com.wyt.headfirst.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedAbstractActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/9.
 */
public class WatchRouterActor extends UntypedAbstractActor {
    public Router router;

    {
        ArrayList<Routee> routers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ActorRef worker = getContext().actorOf(Props.create(MyWorker.class), "worker_" + i);
            getContext().watch(worker);
            routers.add(new ActorRefRoutee(worker));
        }
        router = new Router(new RoundRobinRoutingLogic(), routers);
    }

    @Override
    public void onReceive(Object msg) throws Throwable {
        if (msg instanceof MyWorker.Msg) {//如果msg是MyWorker.Msg类型
            router.route(msg, getSender());//将消息投递给Router即可
        } else if (msg instanceof Terminated) {//当MyWorker已经停止工作
            router = router.removeRoutee(((Terminated) msg).actor());//从工作组中移除
            System.out.println(((Terminated) msg).actor().path() + "is closed,routees=" + router.routees().size());
            if (router.routees().size() == 0) {//如果发现系统中没有可用Actor
                System.out.println("Close system");
                getContext().system().terminate();//直接关闭系统
            }
        } else {
            unhandled(msg);
        }

    }
}

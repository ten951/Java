package com.wyt.headfirst.akka;

import akka.actor.*;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/8.
 */
public class Supervisor extends UntypedAbstractActor {
    //这个自定义的策略是遇到错误后,在1分钟内进行3次重试,如果超过这个频率,那么就会直接杀死Actor;
    private static SupervisorStrategy strategy = new OneForOneStrategy(3, Duration.create(1, TimeUnit.MINUTES),
            param -> {
                if (param instanceof ArithmeticException) {
                    System.out.println("meet ArithmeticException,just resume");
                    return SupervisorStrategy.resume();//不做任何处理
                } else if (param instanceof NullPointerException) {
                    System.out.println("meet NullPointerException,restart");
                    return SupervisorStrategy.restart();//重启Actor
                } else if (param instanceof IllegalActorStateException) {
                    return SupervisorStrategy.stop();//停止Actor
                } else {
                    return SupervisorStrategy.escalate();//向上抛出,让更顶层的Actor处理
                }
            });

    //重写父类的supervisorStrategy()方法,设置使用自定义的监督策略
    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Props) {
            //用来新建一个restartActor,这个子Actor就由当前的Supervisor进行监督了,当Supervisor接收一个Props对象时,就会根据这个Props配置生成一个restartActor.
            getContext().actorOf((Props) message, "restartActor");
        } else {
            unhandled(message);
        }
    }
}

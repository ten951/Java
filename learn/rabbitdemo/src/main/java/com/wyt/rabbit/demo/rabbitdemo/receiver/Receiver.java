package com.wyt.rabbit.demo.rabbitdemo.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Darcy
 * Created By Darcy on 2017/12/12 下午3:04
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver :" + hello);

    }
}

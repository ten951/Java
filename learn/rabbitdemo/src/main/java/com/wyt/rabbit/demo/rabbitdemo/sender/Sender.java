package com.wyt.rabbit.demo.rabbitdemo.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Darcy
 * Created By Darcy on 2017/12/12 下午3:00
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : = " + context);

        amqpTemplate.convertAndSend("hello", context);
    }
}

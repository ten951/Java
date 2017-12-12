package com.wyt.rabbit.demo.rabbitdemo;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Darcy
 * Created By Darcy on 2017/12/12 下午3:06
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }
}

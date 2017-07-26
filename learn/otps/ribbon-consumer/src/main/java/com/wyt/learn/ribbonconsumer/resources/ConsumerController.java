package com.wyt.learn.ribbonconsumer.resources;

import com.wyt.learn.ribbonconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Darcy
 * Created by Darcy on 2017/7/20.
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        // return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
        return helloService.helloService();
    }
}

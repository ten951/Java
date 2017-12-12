package com.wyt.rabbit.demo.rabbitdemo;

import com.wyt.rabbit.demo.rabbitdemo.sender.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitdemoApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    public void contextLoads() {
        sender.send();
    }

}

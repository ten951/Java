package com.wyt.sprboot.spinitdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello World 程序
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/14.
 */
@RestController
@SpringBootApplication //是Spring Boot项目的核心注解,主要目的是开启自动配置,
public class InitApplication {
  /*  @RequestMapping("/")
    String index() {
        return "Hello Spring Boot";
    }*/


    public static void main(String[] args) {
        SpringApplication.run(InitApplication.class, args);
    }
}

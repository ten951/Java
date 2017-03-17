package com.wyt.sprboot.spinitdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 加载配置文件属性
 * @author Darcy
 *         Created by Administrator on 2017/3/14.
 */
@RestController
@SpringBootApplication
public class PropertiesApplication {
    @Value("${book.author}")
    private String bookAuthor;

    @Value("${book.name}")
    private String bookName;

    @RequestMapping("/")
    String index() {
        return "book name is:" + bookName + " and book author is:" + bookAuthor;
    }
    public static void main(String[] args) {
        SpringApplication.run(PropertiesApplication.class, args);
    }
}

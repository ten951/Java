package com.wyt.sprboot.spinitdemo.controller;

import com.wyt.sprboot.spinitdemo.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * SpringMVC+thymeleaf 整合
 * @author Darcy
 *         Created by Administrator on 2017/3/14.
 */
@Controller
@SpringBootApplication
public class IndexApplication {
    @RequestMapping("/")
    public String index(Model model) {
        Person single = new Person("aa", 11);
        ArrayList<Person> people = new ArrayList<>();
        Person p1 = new Person("xx", 11);
        Person p2 = new Person("yy", 22);
        Person p3 = new Person("zz", 33);
        people.add(p1);
        people.add(p2);
        people.add(p3);
        model.addAttribute("singlePerson", single);
        model.addAttribute("people", people);
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(IndexApplication.class, args);
    }
}

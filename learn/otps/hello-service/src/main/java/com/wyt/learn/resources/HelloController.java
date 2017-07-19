package com.wyt.learn.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/19.
 */
@RestController
public class HelloController {
    private final Logger logger = Logger.getLogger(HelloController.class.getName());
    @Qualifier("discoveryClient")
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        String services = "Services:" + client.getServices();
        System.out.println(services);
        return services;
    }
}

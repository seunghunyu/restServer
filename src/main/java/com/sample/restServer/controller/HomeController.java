package com.sample.restServer.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Log4j
@Controller
public class HomeController {
    @GetMapping("/igniteHome")
    public String igniteHome(){
        System.out.println("igniteHome In");
        return "igniteTest";
    }

    @GetMapping("/redisHome")
    public String redisHome(){
        System.out.println("RedisHome In");
        return "RedisTest";
    }
}

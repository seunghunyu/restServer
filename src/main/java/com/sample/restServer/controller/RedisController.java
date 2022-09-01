package com.sample.restServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RedisController {
    @GetMapping("/RedisStart")
    public void hello(HttpSession httpSession) {
        httpSession.setAttribute("testSession", "testRedisSession");
    }
}

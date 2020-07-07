package com.java.netty.controller;

import com.java.netty.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "sendMaaage")
    private void  sendMaaage(){
        testService.sendMessage();
    }


}

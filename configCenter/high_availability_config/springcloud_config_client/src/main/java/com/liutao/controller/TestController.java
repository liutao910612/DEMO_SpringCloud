package com.liutao.controller;

import com.liutao.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

   @Autowired
   private Config config;

    @RequestMapping("/from")
    public String from() {

        return config.getFrom();
    }
}
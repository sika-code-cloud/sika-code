package com.dq.easy.cloud.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {


    @RequestMapping("/index")
    public String test(){ 
        return "index";
    }
}
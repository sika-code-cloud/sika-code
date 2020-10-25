package com.zyd.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/test")
public class AccessHTMLController {
    @RequestMapping("/index")
    public String helloHtml(Map<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        return "index.html";
    }
}
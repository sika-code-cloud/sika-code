package com.easy.cloud.pay.zfb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easy.cloud.core.basic.controller.EcBaseController;

@Controller
public class HelloController extends EcBaseController{
	
    @RequestMapping("/index")
    public String test(){ 
        return "index";
    }
    /**
     * 
     * <p>
     * 重定向到wechatpaytest.html页面
     * </p>
     * 
     * @return
     * @author daiqi
     * 创建时间    2018年2月24日 下午1:44:59
     */
    @RequestMapping("/toZfbPay")
    public String toWechatPayTest(){ 
        return "zfbpaytest";
    }
}
package com.ruoyi.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import common.core.controller.BaseController;

/**
 * build 表单构建
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/tool/build")
public class BuildController extends BaseController
{
    private String prefix = "tool/build";

    @GetMapping()
    public String build()
    {
        return prefix + "/build";
    }
}

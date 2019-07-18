package com.sika.code.standard.base.controller;

import com.sika.code.basic.controller.BaseController;
import com.sika.code.result.Result;
import com.sika.code.result.generator.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 标准框架基础控制类
 *
 * @author daiqi
 * @create 2018-11-29 20:22
 */
public class BaseStandardController extends BaseController {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpSession httpSession;
    @Autowired
    private ResultGenerator resultGenerator;

    protected Result generateResult(Object object) {
        return resultGenerator.generateResult(object);
    }
}

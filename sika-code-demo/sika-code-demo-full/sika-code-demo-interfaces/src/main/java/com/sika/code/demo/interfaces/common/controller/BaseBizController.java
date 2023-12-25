package com.sika.code.demo.interfaces.common.controller;

import com.sika.code.core.base.errorcode.BaseErrorCode;
import com.sika.code.core.result.Result;
import com.sika.code.core.result.generator.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class BaseBizController extends BaseController {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpSession httpSession;
    @Autowired
    protected ResultGenerator resultGenerator;

    protected Result success(Object object) {
        return resultGenerator.generateResult(object);
    }

    protected Result fail(String message) {
        return resultGenerator.generateResultError(message);
    }

    protected Result fail(BaseErrorCode errorCode, String message) {
        return resultGenerator.generateResultError(errorCode, message);
    }

    protected ModelAndView view(String view) {
        return new ModelAndView(view);
    }

    protected ModelAndView view(String view, Map<String, Object> model) {
        return new ModelAndView(view, model);
    }

    protected ModelAndView redirect(String view) {
        return new ModelAndView("redirect:" + view);
    }

    protected ModelAndView forward(String view) {
        return new ModelAndView("forward:" + view);
    }
}

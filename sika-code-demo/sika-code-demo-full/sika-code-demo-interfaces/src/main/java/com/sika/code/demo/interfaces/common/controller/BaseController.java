package com.sika.code.demo.interfaces.common.controller;

import com.sika.code.demo.interfaces.common.log.anotation.ControllerLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础控制类
 *
 * @author daiqi
 * @date 2018年3月18日 上午12:48:58
 */
@ControllerLog
public class BaseController {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

}

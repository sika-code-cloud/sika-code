package com.easy.cloud.core.basic.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.easy.cloud.core.common.date.utils.EcDateFormatUtils;
import com.easy.cloud.core.common.log.annotation.EcLog;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogLevelEnum;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogTypeEnum;
import com.easy.cloud.core.common.log.proxy.impl.EcLogControllerProxy;
import com.easy.cloud.core.exception.handler.EcBaseExceptionHandle;

/**
 * 基础控制类
 * @author daiqi
 * @date 2018年3月18日 上午12:48:58
 */
@Controller
@EcLog(logLevel = EcLogLevelEnum.INFO, logProxyClass = EcLogControllerProxy.class, logType = EcLogTypeEnum.CONTROLLER)
public class EcBaseController extends EcBaseExceptionHandle{
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		DateFormat dateFormat = new SimpleDateFormat(EcDateFormatUtils.FORMAT_NORMAL_DAY);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(DqDateFormatUtils.FORMAT_NORMAL), true));
	}
}

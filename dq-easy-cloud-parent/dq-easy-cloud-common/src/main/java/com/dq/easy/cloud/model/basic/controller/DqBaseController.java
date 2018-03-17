package com.dq.easy.cloud.model.basic.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.dq.easy.cloud.model.common.date.utils.DqDateFormatUtils;
import com.dq.easy.cloud.model.common.log.annotation.DqLog;
import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogLevel;
import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogType;
import com.dq.easy.cloud.model.common.log.entruster.impl.DqLogControllerProxy;
import com.dq.easy.cloud.model.exception.handler.DqBaseExceptionHandle;

/**
 * 基础控制类
 * @author daiqi
 * @date 2018年3月18日 上午12:48:58
 */
@Controller
@DqLog(dqLogLevel = DqLogLevel.INFO, dqLogEntrusterClass = DqLogControllerProxy.class, dqLogType = DqLogType.CONTROLLER)
public class DqBaseController extends DqBaseExceptionHandle{
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		DateFormat dateFormat = new SimpleDateFormat(DqDateFormatUtils.FORMAT_NORMAL_DAY);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(DqDateFormatUtils.FORMAT_NORMAL), true));
	}
}

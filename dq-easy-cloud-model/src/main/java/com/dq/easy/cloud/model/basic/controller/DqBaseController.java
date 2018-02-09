package com.dq.easy.cloud.model.basic.controller;

import org.springframework.stereotype.Controller;

import com.dq.easy.cloud.model.common.log.annotation.DqLog;
import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogLevel;
import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogType;
import com.dq.easy.cloud.model.common.log.entruster.impl.DqLogControllerEntruster;
import com.dq.easy.cloud.model.exception.handler.DqBaseExceptionHandle;

@Controller
@DqLog(dqLogLevel = DqLogLevel.INFO, dqLogEntrusterClass = DqLogControllerEntruster.class, dqLogType = DqLogType.CONTROLLER)
public class DqBaseController extends DqBaseExceptionHandle{

}

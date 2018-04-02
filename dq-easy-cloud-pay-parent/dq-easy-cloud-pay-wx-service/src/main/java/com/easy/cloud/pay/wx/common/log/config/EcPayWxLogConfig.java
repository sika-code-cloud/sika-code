package com.dq.easy.cloud.pay.wx.common.log.config;

import org.springframework.stereotype.Component;

import com.dq.easy.cloud.module.common.log.config.DqLogConfig;
import com.dq.easy.cloud.pay.wx.controller.DqWxPayController;
import com.dq.easy.cloud.pay.wx.logic.DqWxPayLogic;
import com.dq.easy.cloud.pay.wx.service.DqWxPayService;

@Component
public class PayWxLogConfig extends DqLogConfig{
	static{
//		日志开关设置
		setLogSwitchFlag(true, DqWxPayController.class.getName(), "generatePayQrCode");
//		日志开关设置
		setLogSwitchFlag(true, DqWxPayLogic.class.getName(), "generatePayQrCode");
//		日志开关设置
		setLogSwitchFlag(true, DqWxPayService.class.getName(), "generatePayQrCode");
		
//		方法分析开关设置
		setLogAnalysisSwitchFlag(true, DqWxPayController.class.getName(), "generatePayQrCode");
//		方法分析开关设置
		setLogAnalysisSwitchFlag(true, DqWxPayLogic.class.getName(), "generatePayQrCode");
//		方法分析开关设置
		setLogAnalysisSwitchFlag(true, DqWxPayService.class.getName(), "generatePayQrCode");
	}
}

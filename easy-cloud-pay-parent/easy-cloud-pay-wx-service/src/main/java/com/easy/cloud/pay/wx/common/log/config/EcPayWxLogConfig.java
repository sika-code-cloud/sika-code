package com.easy.cloud.pay.wx.common.log.config;

import org.springframework.stereotype.Component;

import com.easy.cloud.core.common.log.config.EcLogConfig;
import com.easy.cloud.pay.wx.controller.EcWxPayController;
import com.easy.cloud.pay.wx.logic.EcWxPayLogic;
import com.easy.cloud.pay.wx.service.EcWxPayService;

@Component
public class EcPayWxLogConfig extends EcLogConfig{
	static{
//		日志开关设置
		setLogSwitchFlag(true, EcWxPayController.class.getName(), "generatePayQrCode");
//		日志开关设置
		setLogSwitchFlag(true, EcWxPayLogic.class.getName(), "generatePayQrCode");
//		日志开关设置
		setLogSwitchFlag(true, EcWxPayService.class.getName(), "generatePayQrCode");
		
//		方法分析开关设置
		setLogAnalysisSwitchFlag(true, EcWxPayController.class.getName(), "generatePayQrCode");
//		方法分析开关设置
		setLogAnalysisSwitchFlag(true, EcWxPayLogic.class.getName(), "generatePayQrCode");
//		方法分析开关设置
		setLogAnalysisSwitchFlag(true, EcWxPayService.class.getName(), "generatePayQrCode");
	}
}

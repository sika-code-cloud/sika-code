package com.easy.cloud.base.log.config;

import org.springframework.stereotype.Component;

import com.easy.cloud.controller.UserController;
import com.easy.cloud.core.common.log.config.EcLogConfig;

/**
 * 用户原子服务日志配置类
 * @author daiqi
 * @date 2018年2月21日 下午4:15:33
 */
@Component
public class UserAtomLogConfig extends EcLogConfig{
	static{
//		日志开关设置
		setLogSwitchFlag(true, UserController.class.getName());
		
//		方法分析开关设置
		setLogAnalysisSwitchFlag(false, UserController.class.getName());
	}
}

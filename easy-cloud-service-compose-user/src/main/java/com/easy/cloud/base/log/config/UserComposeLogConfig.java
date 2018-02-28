package com.easy.cloud.base.log.config;

import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.common.log.config.DqLogConfig;
import com.easy.cloud.controller.UserController;

/**
 * 
 * <p>
 * 用户组合服务基础日志配置类
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月9日 下午8:01:52
 */
@Component
public class UserComposeLogConfig extends DqLogConfig{

	static{
		setLogSwitchFlag(false, UserController.class.getName());
		setLogSwitchFlag(false, UserController.class.getName(), "login");
	}
}

package com.easy.cloud.core.common.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogLevelEnum;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogModeEnum;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogTypeEnum;
import com.easy.cloud.core.common.log.proxy.EcLogAbstractProxy;
import com.easy.cloud.core.common.log.proxy.impl.EcLogBaseProxy;

/**
 * 
 * <p>
 *  日志记录注解
 * </p>
 *
 * @author daiqi 创建时间 2018年2月7日 下午4:57:04
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EcLog {
	/** 日志记录方式 详情@Link DqLogConstant.DqLogMode */
	EcLogModeEnum logMode() default EcLogModeEnum.CONSOLE;

	/** 日志记录级别 详情@Link DqLogConstant.DqLogLevel */
	EcLogLevelEnum logLevel() default EcLogLevelEnum.DEBUG;
	
	/** 日志处理基础委托类class */
	Class<? extends EcLogAbstractProxy> logProxyClass() default EcLogBaseProxy.class;
	
	/** 日志记录类型 详情@Link DqLogConstant.DqLogType */
	EcLogTypeEnum logType() default EcLogTypeEnum.OTHER;
	
	/** 日志开关---false则关闭日志记录---默认打开 */
	boolean logSwitch() default true;
	
	/** 日志分析开关--false则关闭日志分析---默认为打开 */
	boolean logAnalysisSwitch() default true;
	
}

package com.dq.easy.cloud.model.common.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogLevel;
import com.dq.easy.cloud.model.common.log.constant.DqLogConstant.DqLogMode;

/**
 * 
 * <p>
 *  日志记录开启注解
 * </p>
 *
 * @author daiqi 创建时间 2018年2月7日 下午4:57:04
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DqLogOpen {
	/** 日志记录方式 详情@Link DqLogConstant.DqLogMode */
	public int dqLogMode() default DqLogMode.CONSOLE;

	/** 日志记录级别 详情@Link DqLogConstant.DqLogLevel */
	public int dqLogLevel() default DqLogLevel.DEBUG;
	
	/** 日志记录类型 详情@Link DqLogConstant.DqLogType */
	public int dqLogType();
}

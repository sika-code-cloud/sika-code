package com.easy.cloud.core.jdbc.auditor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.easy.cloud.core.jdbc.auditor.procced.EcBaseAuditProcced;
import com.easy.cloud.core.jdbc.auditor.procced.impl.EcDefaultAuditProcced;

/**
 * 
 * <p>
 * 审计注解
 * </p>
 * 
 * <pre>
 * 注解在需要开启审计的方法上
 * </pre>
 * 
 * @author daiqi
 * @创建时间 2018年5月5日 上午11:00:40
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EcAuditAnnotation {
	/** 操作类型常量---参考EcAuditConstant.EcActionType类 */
	int actionType();

	/** 类型 1：保存，2：更新---参考EcAuditConstant.EcType */
	int type();

	/** 处理审计的class 默认使用EcAuditBO */
	Class<? extends EcBaseAuditProcced> proccedClass() default EcDefaultAuditProcced.class;
}

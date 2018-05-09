package com.easy.cloud.core.jdbc.base.primarykey.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.easy.cloud.core.jdbc.base.primarykey.EcBasePrimaryKeyGenerator;

/**
 * 
 * <p>
 * 主键生成器注解
 * </p>
 *
 *
 * @author daiqi
 * @创建时间 2018年5月8日 下午8:37:07
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EcGenericGenerator {
	/** 主键生成器class */
	Class<? extends EcBasePrimaryKeyGenerator> primaryKeyGeneratorClass();
}

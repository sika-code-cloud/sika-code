package com.sika.code.database.primarykey.annotation;

import com.sika.code.database.primarykey.BaseKeyGenerator;

import java.lang.annotation.*;

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
public @interface GenericGenerator {
	/** 主键生成器class */
	Class<? extends BaseKeyGenerator> primaryKeyGeneratorClass();
}

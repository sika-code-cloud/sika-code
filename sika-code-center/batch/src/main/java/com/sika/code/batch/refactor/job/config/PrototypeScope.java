package com.sika.code.batch.refactor.job.config;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author daiqi
 * @create 2020-01-09 0:34
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope(value = "prototype")
public @interface PrototypeScope {
}

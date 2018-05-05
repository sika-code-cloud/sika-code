package com.easy.cloud.core.jdbc.auditor.aware;

import org.springframework.data.domain.AuditorAware;

/**
 * 
 * <p>
 * 审计者接口---子类可以通过实现该接口动态获取创建者和更新者
 * </p>
 *
 * @param <T> : 创建者或更新者的属性类型
 * @author daiqi
 * @创建时间 2018年5月5日 下午2:58:09
 */
public interface EcAuditorAware<T> extends AuditorAware<T> {

}
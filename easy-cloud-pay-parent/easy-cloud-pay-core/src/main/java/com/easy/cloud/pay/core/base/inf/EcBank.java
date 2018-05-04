package com.easy.cloud.pay.core.base.inf;

/**
 * 
 * <p>
 * 银行
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:11:49
 */
public interface EcBank {

    /**
     * 获取银行的代码
     * @return 银行的代码
     */
    String getCode();

    /**
     * 获取银行的名称
     * @return 银行的名称
     */
    String getName();
}

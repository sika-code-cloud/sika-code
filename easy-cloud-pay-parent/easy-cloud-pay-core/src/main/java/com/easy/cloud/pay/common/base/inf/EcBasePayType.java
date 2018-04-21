package com.easy.cloud.pay.common.base.inf;

import com.easy.cloud.pay.common.transaction.inf.EcTransactionType;

/**
 * 
 * <p>
 * 基础的支付类型
 * </p>
 * 
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:08:00
 */
public interface EcBasePayType {


    /**
     * 根据支付类型获取交易类型
     * @param transactionType 类型值
     * @return  交易类型
     */
    EcTransactionType getTransactionType(String transactionType);

}

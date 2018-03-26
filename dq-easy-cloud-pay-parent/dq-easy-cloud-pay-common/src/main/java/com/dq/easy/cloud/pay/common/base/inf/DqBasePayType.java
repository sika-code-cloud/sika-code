package com.dq.easy.cloud.pay.common.base.inf;

import com.dq.easy.cloud.pay.common.transaction.inf.DqTransactionType;

/**
 * 
 * <p>
 * 基础的支付类型
 * </p>
 * 
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:08:00
 */
public interface DqBasePayType {


    /**
     * 根据支付类型获取交易类型
     * @param transactionType 类型值
     * @return  交易类型
     */
    DqTransactionType getTransactionType(String transactionType);

}

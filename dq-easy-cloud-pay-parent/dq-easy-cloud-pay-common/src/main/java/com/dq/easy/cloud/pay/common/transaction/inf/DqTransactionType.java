package com.dq.easy.cloud.pay.common.transaction.inf;

/**
 * 
 * <p>
 * 交易类型接口
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:06:24
 */
public interface DqTransactionType {
    /**
     * 获取交易类型
     * @return 交易类型
     */
     String getType();

    /**
     * 获取接口
     * @return 接口
     */
     String getMethod();
     
     /**
      * 获取响应key
      * @return 接口
      */
      String getResponseKey();
}


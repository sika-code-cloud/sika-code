package com.sika.code.consumer.logic;

import com.sika.code.consumer.pojo.dto.BaseConsumerDTO;

/**
 * 基础消费业务逻辑处理类
 *
 * @author daiqi
 * @create 2018-08-22 20:18
 */
public interface BaseConsumerLogic {
    /**
     * <p>
     * 是否重复提交
     * </p>
     *
     * @param baseConsumerDTO
     * @return boolean
     * @author daiqi
     * @date 2018/8/22 20:58
     */
    boolean isReSubmit(BaseConsumerDTO baseConsumerDTO);

    /**
     * <p>
     * 消费
     * </p>
     *
     * @param baseConsumerDTO
     * @return boolean
     * @author daiqi
     * @date 2018/8/22 20:58
     */
    boolean consumer(BaseConsumerDTO baseConsumerDTO);
}

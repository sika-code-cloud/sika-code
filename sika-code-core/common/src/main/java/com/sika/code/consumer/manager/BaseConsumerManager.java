package com.sika.code.consumer.manager;

import com.sika.code.consumer.logic.BaseConsumerLogic;
import com.sika.code.consumer.pojo.dto.BaseConsumerDTO;
import com.sika.code.consumer.selector.BaseConsumerLogicSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author daiqi
 * @create 2018-08-23 9:48
 */
@Component
public class BaseConsumerManager {
    @Autowired
    private BaseConsumerLogicSelector baseConsumerLogicSelector;

    /**
     * <p>
     * 消费信息
     * </p>
     *
     * @param baseConsumerDTO
     * @return ServiceResult
     * @author daiqi
     * @date 2018/8/23 10:48
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean consumer(BaseConsumerDTO baseConsumerDTO) {
        BaseConsumerLogic consumerLogic = baseConsumerLogicSelector.selectConsumerLogic(baseConsumerDTO);
        if (consumerLogic.isReSubmit(baseConsumerDTO)) {
            return true;
        }
        return consumerLogic.consumer(baseConsumerDTO);
    }

}

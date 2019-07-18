package com.sika.code.consumer.selector;

import com.sika.code.basic.util.Assert;
import com.sika.code.consumer.logic.BaseConsumerLogic;
import com.sika.code.consumer.pojo.dto.BaseConsumerDTO;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 消费业务逻辑对象选择器
 *
 * @author daiqi
 * @create 2018-08-23 8:59
 */
public abstract class BaseConsumerLogicSelector {
    private static Map<Integer, BaseConsumerLogic> consumerLogicMap = Maps.newConcurrentMap();

    /**
     * <p>
     * 选择消费者逻辑对象
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     dataType : 1 : 数据类型 : 是
     * </pre>
     *
     * @param consumerDTO
     * @return BaseConsumerLogic
     * @author daiqi
     * @date 2018/8/23 10:11
     */
    public BaseConsumerLogic selectConsumerLogic(BaseConsumerDTO consumerDTO) {
        Assert.verifyObjNull(consumerDTO);
        Assert.verifyObjNull(consumerDTO.getDataType(), "dataType");
        BaseConsumerLogic consumerLogic = selectByType(consumerDTO);
        Assert.verifyDataNotExistent(consumerLogic, "consumerLogic");
        return consumerLogic;
    }

    protected BaseConsumerLogic selectByType(BaseConsumerDTO consumerDTO) {
        return get(consumerDTO.getDataType());
    }


    public void putToMap(Integer dataType, BaseConsumerLogic consumerLogic) {
        consumerLogicMap.put(dataType, consumerLogic);
    }

    public BaseConsumerLogic get(Integer dataType) {
        return consumerLogicMap.get(dataType);
    }

    /**
     * <p>
     * put消费者业务逻辑bean到map中
     * </p>
     *
     * @param
     * @return void
     * @author daiqi
     * @date 2018/8/23 10:14
     */
    protected abstract void putLogicsToMap();


}

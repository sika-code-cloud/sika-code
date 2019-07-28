package com.sika.code.mq.common.constant;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * mq的数据类型类 --- 简单情况下可以复用相同的队列处理不同类型的消息
 *
 * @author daiqi
 * @create 2019-07-06 14:40
 */
@Data
@Accessors(chain = true)
public class MqDataType {
    /**
     * 主编码
     */
    private String majorCode;
    /**
     * 次编码
     */
    private String secondaryCOde;
    /**
     * 描述
     */
    private String desc;
}

package com.sika.code.mq.rabbit.common.constant;

import com.sika.code.basic.constant.TypeEnumInf;

/**
 * 交换机的枚举类型
 *
 * @author daiqi
 * @create 2019-07-06 19:11
 */
public interface MqBindingConfigEnum extends TypeEnumInf {
    /**
     * 绑定的规则Key
     */
    String getRoutingKey();


}

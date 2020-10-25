package com.sika.code.mq.rabbit.common.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.common.string.constant.StringConstant;

/**
 * 交换机的枚举类型
 *
 * @author daiqi
 * @create 2019-07-06 19:11
 */
public interface MqExchangeConfigEnum extends TypeEnumInf {
    /**
     * 交换机名称
     */
    String getName();

    /**
     * 交换机类型
     */
    MqExchangeType getExchangeType();

    /**
     * 是否持久化
     * <p>
     * 是返回true 否则返回false
     */
    boolean isDurable();

    /**
     * 是否自动删除交换机
     * <p>
     * 是返回true 否则返回false
     */
    boolean isAutoDelete();

    default String getNameFull(String name, MqExchangeType exchangeType) {
        return name + StringConstant.Symbol.STOP + exchangeType.getTypeName();
    }

}

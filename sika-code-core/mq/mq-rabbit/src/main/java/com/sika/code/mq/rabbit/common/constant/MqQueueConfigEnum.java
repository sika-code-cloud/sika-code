package com.sika.code.mq.rabbit.common.constant;

import com.sika.code.basic.constant.TypeEnumInf;

/**
 * 消息队列枚举类型
 * @author daiqi
 * @create 2019-07-06 16:54
 */
public interface MqQueueConfigEnum extends TypeEnumInf {

    /**
     * 队列名称
     */
    String getName();

    /**
     * 是否持久化
     *
     * 是返回true 否则返回false
     */
    boolean isDurable();

    /**
     * 是否是排他队列
     *
     * 是返回true 否则返回false
     */
    boolean isExclusive();

    /**
     * 是否自动删除队列
     *
     * 是返回true 否则返回false
     */
    boolean isAutoDelete();
}

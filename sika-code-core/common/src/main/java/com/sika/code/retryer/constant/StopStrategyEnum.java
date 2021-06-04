package com.sika.code.retryer.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 重试停止策略枚举
 *
 * @author daiqi
 * @create 2019-12-05 22:32
 */
@Getter
@AllArgsConstructor
public enum StopStrategyEnum implements TypeEnumInf {
    /**
     * 不停止，用于需要一直轮训直到返回期望结果的情况
     */
    NEVER_STOP(1, "不停止"),
    /**
     * 设定最大重试次数，如果超出最大重试次数则停止重试，并返回重试异常；
     */
    STOP_AFTER_ATTEMPT(2, "设定最大重试次数"),
    /**
     * 设定一个最长允许的执行时间；比如设定最长执行10s，无论任务执行次数，只要重试的时候超出了最长时间，则任务终止，并返回重试异常RetryException
     */
    STOP_AFTER_DELAY(3, "设定一个最长允许的执行时间"),
    ;
    private Integer type;
    private String desc;

}

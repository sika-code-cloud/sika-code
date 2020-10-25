package com.sika.code.retryer.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 等待时长策略（控制时间间隔） --- 发生异常后等待多久进行重试
 *
 * @author daiqi
 * @create 2019-12-05 22:38
 */
@AllArgsConstructor
@Getter
public enum WaitStrategyEnum implements TypeEnumInf {
    /**
     * 固定等待时
     */
    FIXED(1, "固定等待时长策略"),
    /**
     * 随机等待时长策略（可以提供一个最小和最大时长，等待时长为其区间随机值）
     */
    RANDOM(2, "随机等待时长策略"),
    /**
     * 递增等待时长策略（提供一个初始值和步长，等待时间随重试次数增加而增加）
     */
    INCREMENTING(3, "递增等待时长策略"),
    /**
     * 1、这中策略等待时间呈指数形式增长，指数形式增长，
     * 2、如果指定最大等待时间，则增长到最大等待时间就不再增长；
     * 3、如果没有指定最大等待时间，则最大等待时间为Long.MAX_VALUE
     */
    EXPONENTIAL(4, "：指数等待时长策略"),
    /**
     * 等待时间以斐波拉契数列形式增长
     * 1、无参：等待时间从 1 增长到 Long.MAX_VALUE
     * 2、两个参数：等待时间从 1 增长到 maximumTimeUnit.toMillis(maximumTime)，到最大值以后等待时间恒定不变
     * 3、三个参数：等待时间从 multiplier 增长到 maximumTimeUnit.toMillis(maximumTime)
     */
    FIBONACCI(5, "：FIBONACCI 等待时长策略；"),
    ;
    private Integer type;
    private String desc;

}

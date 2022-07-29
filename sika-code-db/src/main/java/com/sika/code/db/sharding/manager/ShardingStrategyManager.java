package com.sika.code.db.sharding.manager;


import com.sika.code.core.util.BeanUtil;
import com.sika.code.db.sharding.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 * <p>
 *     分片策略管理器
 * </p >
 * @author sikadai
 * @since 2022/7/9 15:06
 */
@Slf4j
public class ShardingStrategyManager {
    private final Map<Class<? extends Strategy>, Strategy> strategies = new ConcurrentHashMap<>(16);

    /**
     * 向管理器中添加策略
     *
     * @param strategyClassName
     * @param strategy
     */
    public void addStrategy(Class<? extends Strategy> strategyClassName, Strategy strategy) {
        strategies.put(strategyClassName, strategy);
    }

    /**
     * <p>
     * 获取擦略类
     * </p >
     *
     * @param strategyClassName : 策略类名称
     * @return com.ak.cloud.standard.frame.db.sharing.strategy.Strategy
     * @author sikadai
     * @since 2022/7/9 15:15
     */
    public Strategy getStrategy(Class<? extends Strategy> strategyClassName) {
        Strategy strategyFromCache = strategies.get(strategyClassName);
        if (strategyFromCache == null) {
            addStrategy(strategyClassName, BeanUtil.getBean(strategyClassName));
        }
        return strategies.get(strategyClassName);
    }

    public Map<Class<? extends Strategy>, Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(Map<Class<? extends Strategy>, String> strategies) {
        for (Map.Entry<Class<? extends Strategy>, String> entry : strategies.entrySet()) {
            addStrategy(entry.getKey(), BeanUtil.getBean(entry.getKey()));
        }
    }

}
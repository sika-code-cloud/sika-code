package com.sika.code.retryer.config;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 重试配置类
 *
 * @author daiqi
 * @create 2019-12-05 22:29
 */
public class RetryerConfig {


    public static void main(String[] args) {
        Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
                .retryIfResult(Predicates.isNull())
                .retryIfExceptionOfType(Exception.class)
                .retryIfRuntimeException()
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withWaitStrategy(WaitStrategies.fixedWait(2L, TimeUnit.SECONDS))
                .build();
        List<Integer> list = Lists.newArrayList();
        try {
            String name = retryer.call(() -> {
                System.out.println("运行第" + (list.size() + 1));
                if (list.size() < 2) {
                    list.add(1);
                    throw new RuntimeException("lalal-----" + list);
                }
                return "zhangsan";
            });
            System.out.println(name + list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

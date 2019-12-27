package com.sika.code.batch.service;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.google.common.collect.Lists;
import com.sika.code.retryer.anotation.RetryerAnnotation;
import com.sika.code.retryer.constant.WaitStrategyEnum;
import com.sika.code.retryer.factory.RetryerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author daiqi
 * @create 2019-12-20 11:03
 */
@Service
@Slf4j
//@RetryerAnnotation
public class TestService {
    public static int times = 1;

    public boolean testRetry() throws Exception {
        Retryer<Boolean> retryer = RetryerFactory.newDefaultRetryer();
        return retryer.call(() -> core1());
    }

    @RetryerAnnotation(waitStrategyEnum = WaitStrategyEnum.INCREMENTING, increment = 5, attemptNumber = 5)
    public boolean testRetry1() {
        return core1();
    }

    private boolean core1() {
        for (int i = 0; i < 100; ++i) {
            Lists.newArrayList();
        }
//        core();
        return true;
    }

    private boolean core() {
        times++;
        log.info("call times={}", times);
        if (times == 2) {
            log.info("NullPointerException:" + times);
            throw new NullPointerException("空指针");
        } else if (times == 3) {
            log.info("NumberFormatException:" + times);
            throw new NumberFormatException("数值转换异常");
        } else if (times == 4) {
            log.info("RuntimeException:" + times);
            throw new RuntimeException("运行时");
        } else if (times == 5) {
            log.info("false:" + times);
            return false;
        } else {
            return true;
        }
    }
}

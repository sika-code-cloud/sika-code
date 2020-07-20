package com.retryer;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author daiqi
 * @create 2019-12-19 18:15
 */

@Slf4j
public class TestRetryer {
    @Test
    public void testRetry() throws Exception {
        testException();
    }

    public Object testException() throws Exception {
        //定义重试机制
        Retryer<Object> retryer = RetryerBuilder.newBuilder()
                //retryIf 重试条件
                .retryIfExceptionOfType(NullPointerException.class)
                .retryIfExceptionOfType(NumberFormatException.class)
                .retryIfResult(Predicates.equalTo(1))
                .retryIfResult(Predicates.equalTo(false))

                //等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        if(attempt.hasResult()) {
                            log.info("attempt.hasResult：" + attempt.getResult());
                        }
                        if (attempt.hasException()) {
                            log.error("attemp.hasException:" , attempt.getExceptionCause());
                        }
                    }
                })

                //停止策略 : 尝试请求6次
                .withStopStrategy(StopStrategies.stopAfterAttempt(6))


                .build();

        //定义请求实现
        Callable<Object> callable = new Callable<Object>() {
            int times = 1;

            @Override
            public Boolean call() throws Exception {
                log.info("call times={}", times);
                times++;

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
        };
        //利用重试器调用请求
        return retryer.call(callable);
    }
}

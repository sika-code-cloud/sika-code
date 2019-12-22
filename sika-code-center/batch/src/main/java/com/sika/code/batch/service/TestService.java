package com.sika.code.batch.service;

import com.sika.code.retryer.anotation.RetryerAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author daiqi
 * @create 2019-12-20 11:03
 */
@Service
@Slf4j
@RetryerAnnotation
public class TestService {
    public static int times = 1;

    public boolean testRetry() {
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
    @RetryerAnnotation(attemptNumber = 3, retryIfExceptionOfTypes = {NullPointerException.class, NumberFormatException.class})
    public boolean testRetry1() {
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

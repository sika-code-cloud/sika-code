package com.sika.code.lock.pojo.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.locks.Lock;

/**
 * 尝试加锁的结果
 *
 * @author daiqi
 * @create 2019-07-28 22:36
 */
@Data
@AllArgsConstructor
public class LockResult {
    protected Lock lock;

    public boolean isSuccess() {
        return true;
    }
}

package com.sika.code.lock.pojo.result;

import lombok.Data;

import java.util.concurrent.locks.Lock;

/**
 * @author daiqi
 * @create 2019-07-28 22:38
 */
@Data
public class TryLockResult extends LockResult{
    private boolean success;

    public TryLockResult(Lock lock, boolean success) {
        super(lock);
        this.success = success;
    }
}

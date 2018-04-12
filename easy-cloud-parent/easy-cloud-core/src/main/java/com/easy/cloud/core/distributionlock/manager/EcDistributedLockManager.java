package com.easy.cloud.core.distributionlock.manager;

import org.springframework.stereotype.Component;

import com.easy.cloud.core.distributionlock.annotation.EcDistributedLock;
import com.easy.cloud.core.distributionlock.worker.Worker1;

@Component
public class EcDistributedLockManager {

    @EcDistributedLock(argNum = 1, lockNameSuffix = ".lock")
    public Integer aspect(String lockName, Worker1 worker1) {
        return worker1.aspectBusiness(lockName);
    }

}
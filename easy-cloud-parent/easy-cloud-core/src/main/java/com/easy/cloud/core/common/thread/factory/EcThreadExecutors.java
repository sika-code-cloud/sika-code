package com.easy.cloud.core.common.thread.factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;

import com.easy.cloud.core.common.thread.pool.EcThreadPoolExecutor;

/**
 * 创建连接池的工厂累
 * @author daiqi
 * @date 2018年4月28日 下午10:33:18
 */
public class EcThreadExecutors {
	public static ExecutorService newCachedThreadPool() {
        return new EcThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
    }
}

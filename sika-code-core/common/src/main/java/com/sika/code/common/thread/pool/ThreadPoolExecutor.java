package com.sika.code.common.thread.pool;

import com.sika.code.common.log.util.LogUtil;
import com.sika.code.common.thread.factory.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 自定义线程池
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年5月7日 下午3:41:34
 */
public class ThreadPoolExecutor extends java.util.concurrent.ThreadPoolExecutor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        Executors.addCreateThreadPool(this);
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                              BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        Executors.addCreateThreadPool(this);
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                              BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        Executors.addCreateThreadPool(this);
    }

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                              BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        Executors.addCreateThreadPool(this);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        long endTime = System.currentTimeMillis();
        long start = startTime.get();
        long useTime = endTime - start;
        numTasks.incrementAndGet();
        totalTime.addAndGet(useTime);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        startTime.set(System.currentTimeMillis());
    }

    @Override
    protected void terminated() {
        LogUtil.debug("terminated", "terminated avg time " + totalTime.get() + " " + numTasks.get(), logger);
    }

}

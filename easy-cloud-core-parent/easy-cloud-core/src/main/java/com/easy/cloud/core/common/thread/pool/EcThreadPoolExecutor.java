package com.easy.cloud.core.common.thread.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.common.log.utils.EcLogUtils;

/**
 * 
 * <p>
 * 自定义线程池
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年5月7日 下午3:41:34
 */
public class EcThreadPoolExecutor extends ThreadPoolExecutor {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ThreadLocal<Long> startTime = new ThreadLocal<>();
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();

	public EcThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	public EcThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
	}

	public EcThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
	}

	public EcThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		try {
			long endTime = System.currentTimeMillis();
			long start = startTime.get();
			long useTime = endTime - start;
			numTasks.incrementAndGet();
			totalTime.addAndGet(useTime);
//			EcLogUtils.info(Thread.currentThread().getName() + "线程执行时间", useTime, logger);
		} finally {
			super.afterExecute(r, t);
		}
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		startTime.set(System.currentTimeMillis());
	}

	@Override
	protected void terminated() {
		try {
			EcLogUtils.debug("terminated", "terminated avg time " + totalTime.get() + " " + numTasks.get(), logger);
		} finally {
			super.terminated();
		}
	}

}

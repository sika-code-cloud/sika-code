package com.easy.cloud.core.common.thread.pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.common.thread.factory.EcExecutors;

/**
 * 
 * <p>
 * 定时任务线程连接池
 * </p>
 *
 * <pre>
 *  说明：对定时任务连接池进行封装
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年5月7日 下午2:31:53
 */
public class EcScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ThreadLocal<Long> startTime = new ThreadLocal<>();
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();

	public EcScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
		super(corePoolSize, handler);
		EcExecutors.addCreateThreadPool(this);
	}

	public EcScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory,
			RejectedExecutionHandler handler) {
		super(corePoolSize, threadFactory, handler);
		EcExecutors.addCreateThreadPool(this);
	}

	public EcScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
		super(corePoolSize, threadFactory);
		EcExecutors.addCreateThreadPool(this);
	}

	public EcScheduledThreadPoolExecutor(int corePoolSize) {
		super(corePoolSize);
		EcExecutors.addCreateThreadPool(this);
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

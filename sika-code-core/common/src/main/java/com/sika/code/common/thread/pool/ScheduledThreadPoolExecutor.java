package com.sika.code.common.thread.pool;

import com.sika.code.common.log.util.LogUtil;
import com.sika.code.common.thread.factory.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

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
public class ScheduledThreadPoolExecutor extends java.util.concurrent.ScheduledThreadPoolExecutor {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ThreadLocal<Long> startTime = new ThreadLocal<>();
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();

	public ScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
		super(corePoolSize, handler);
		Executors.addCreateThreadPool(this);
	}

	public ScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory,
                                       RejectedExecutionHandler handler) {
		super(corePoolSize, threadFactory, handler);
		Executors.addCreateThreadPool(this);
	}

	public ScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
		super(corePoolSize, threadFactory);
		Executors.addCreateThreadPool(this);
	}

	public ScheduledThreadPoolExecutor(int corePoolSize) {
		super(corePoolSize);
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

package com.easy.cloud.core.common.thread.pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
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
public class EcScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
	private final ThreadLocal<Long> startTime = new ThreadLocal<>();
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();

	public EcScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
		super(corePoolSize, handler);
	}

	public EcScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory,
			RejectedExecutionHandler handler) {
		super(corePoolSize, threadFactory, handler);
	}

	public EcScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
		super(corePoolSize, threadFactory);
	}

	public EcScheduledThreadPoolExecutor(int corePoolSize) {
		super(corePoolSize);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		System.out.println("beforeExecute " + r);
		startTime.set(System.currentTimeMillis());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void terminated() {
		try {
			System.out.println("terminated avg time " + totalTime.get() + " " + numTasks.get());
		} finally {
			super.terminated();
		}
	}
}

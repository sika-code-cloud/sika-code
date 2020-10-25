package com.sika.code.common.thread.factory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * <p>
 * 创建线程的工厂类
 * </p>
 *
 * <pre>
 *  说明：提供创建线程的规则
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年5月7日 下午2:58:47
 */
public class ThreadFactory implements java.util.concurrent.ThreadFactory {
	private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    /**
     * 
     * @param namePrefix : String : 线程的前缀名称，用来区分不同连接池的线程
     */
    public ThreadFactory(String namePrefix) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                              Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix +"-pool-" +
                      poolNumber.getAndIncrement() +
                     "-thread-";
    }
    
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                              namePrefix + threadNumber.getAndIncrement(),
                              0);
//        t.setDaemon(true);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

}

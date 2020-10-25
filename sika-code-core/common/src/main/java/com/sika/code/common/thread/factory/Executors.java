package com.sika.code.common.thread.factory;

import com.sika.code.common.log.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * 创建线程池的工厂类
 *
 * @author daiqi
 * @date 2018年4月28日 下午10:33:18
 */
public class Executors {
    private static final Logger LOGGER = LoggerFactory.getLogger(Executors.class);
    /**
     * 保持有效时间---默认---60
     */
    private static final long KEEP_ALIVE_TIME_DEFAULT = 60;
    /**
     * 核心线程池的大小---0
     */
    private static final int CORE_POOL_SIZE_ZERO = 0;
    /**
     * 保存创建的线程池对象的容器
     */
    private static final List<ExecutorService> CREATE_THREAD_POOL_LIST = new Vector<>();

    public static void addCreateThreadPool(ExecutorService executorService) {
        CREATE_THREAD_POOL_LIST.add(executorService);
    }

    public synchronized static void shutDownAllExecutorService() {
        for (ExecutorService executorService : CREATE_THREAD_POOL_LIST) {
            executorService.shutdown();
        }
        LogUtil.info("总共停止的线程池数为", CREATE_THREAD_POOL_LIST.size(), LOGGER);
    }

    public static void shutDownNowAllExecutorService() {
        for (ExecutorService executorService : CREATE_THREAD_POOL_LIST) {
            executorService.shutdownNow();
        }
    }

    /**
     * <p>
     * 可以无限扩大的线程池
     * </p>
     * <p>
     * <pre>
     * 是一个可以无限扩大的线程池
     * 它比较适合处理执行时间比较小的任务；
     * corePoolSize=0，maximumPoolSize为无限大，意味着线程数量可以无限大；
     * keepAliveTime=60S，意味着线程空闲时间超过60S就会被杀死；
     * 采用SynchronousQueue装等待的任务，这个阻塞队列没有存储空间，这意味着只要有请求到来，就必须要找到一条工作线程处理他，如果当前没有空闲的线程，那么就会再创建一条新的线程。
     * </pre>
     *
     * @return ExecutorService
     * @author daiqi
     * @创建时间 2018年5月7日 下午2:22:36
     */
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(CORE_POOL_SIZE_ZERO, Integer.MAX_VALUE, KEEP_ALIVE_TIME_DEFAULT,
                TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }

    /**
     * <p>
     * 可以无限扩大的线程池
     * </p>
     * <p>
     * <pre>
     * 是一个可以无限扩大的线程池
     * 它比较适合处理执行时间比较小的任务；
     * corePoolSize=0，maximumPoolSize为无限大，意味着线程数量可以无限大；
     * keepAliveTime=60S，意味着线程空闲时间超过60S就会被杀死；
     * 采用SynchronousQueue装等待的任务，这个阻塞队列没有存储空间，这意味着只要有请求到来，就必须要找到一条工作线程处理他，如果当前没有空闲的线程，那么就会再创建一条新的线程。
     * </pre>
     *
     * @param threadFactory : ThreadFactory : 线程工厂
     * @return ExecutorService
     * @author daiqi
     * @创建时间 2018年5月7日 下午2:22:36
     */
    public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(CORE_POOL_SIZE_ZERO, Integer.MAX_VALUE, KEEP_ALIVE_TIME_DEFAULT,
                TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), threadFactory);
    }

    /**
     * <p>
     * 创建固定大小的线程池
     * </p>
     * <p>
     * <pre>
     * 是一种固定大小的线程池；
     * corePoolSize和maximunPoolSize都为用户设定的线程数量nThreads；
     * keepAliveTime为0，意味着一旦有多余的空闲线程，就会被立即停止掉；
     * 但这里keepAliveTime无效；
     * 阻塞队列采用了LinkedBlockingQueue，它是一个无界队列；由于阻塞队列是一个无界队列，因此永远不可能拒绝任务；由于采用了无界队列，实际线程数量将永远维持在nThreads，因此maximumPoolSize和keepAliveTime将无效。
     * </pre>
     *
     * @param nThreads : int : 核心线程数和最大线程数量
     * @return ExecutorService
     * @author daiqi
     * @创建时间 2018年5月7日 下午2:15:11
     */
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    /**
     * <p>
     * 创建固定大小的线程池
     * </p>
     * <p>
     * <pre>
     * 是一种固定大小的线程池；
     * corePoolSize和maximunPoolSize都为用户设定的线程数量nThreads；
     * keepAliveTime为0，意味着一旦有多余的空闲线程，就会被立即停止掉；
     * 但这里keepAliveTime无效；
     * 阻塞队列采用了LinkedBlockingQueue，它是一个无界队列；由于阻塞队列是一个无界队列，因此永远不可能拒绝任务；由于采用了无界队列，实际线程数量将永远维持在nThreads，因此maximumPoolSize和keepAliveTime将无效。
     * </pre>
     *
     * @param nThreads      : int : 核心线程数和最大线程数量
     * @param threadFactory : ThreadFactory : 线程工厂
     * @return
     * @author daiqi
     * @创建时间 2018年5月7日 下午2:28:39
     */
    public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), threadFactory);
    }

    /**
     * <p>
     * 创建只有一个线程的线程池---若该线程发生异常将会自动创建一个线程以保证线程不会因为异常而消失
     * </p>
     * <p>
     * <pre>
     *  只会创建一条工作线程处理任务；
     * 	采用的阻塞队列为LinkedBlockingQueue；
     * </pre>
     *
     * @return
     * @author daiqi
     * @创建时间 2018年5月7日 下午2:44:12
     */
    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService(
                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()));
    }

    /**
     * <p>
     * 创建只有一个线程的线程池---若该线程发生异常将会自动创建一个线程以保证线程不会因为异常而消失
     * </p>
     * <p>
     * <pre>
     *  只会创建一条工作线程处理任务；
     * 	采用的阻塞队列为LinkedBlockingQueue；
     * </pre>
     *
     * @return
     * @author daiqi
     * @创建时间 2018年5月7日 下午2:44:12
     */
    public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory) {
        return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), threadFactory));
    }

    /**
     * <p>
     * 单线程池定时任务
     * </p>
     * <p>
     * <pre>
     * 若该线程发生异常将会自动创建一个线程以保证线程不会因为异常而消失
     * </pre>
     *
     * @param
     * @return java.manager.concurrent.ExecutorService
     * @author daiqi
     * @date 2018/7/26 17:11
     */
    public static ExecutorService newSingleThreadScheduledExecutor() {
        return new DelegatedScheduledExecutorService(new ScheduledThreadPoolExecutor(1));
    }

    /**
     * <p>
     * 单线程池定时任务
     * </p>
     * <p>
     * <pre>
     * 若该线程发生异常将会自动创建一个线程以保证线程不会因为异常而消失
     * </pre>
     *
     * @param threadFactory
     * @return
     * @author daiqi
     * @创建时间 2018年5月7日 下午2:46:19
     */
    public static ExecutorService newSingleThreadScheduledExecutor(ThreadFactory threadFactory) {
        return new DelegatedScheduledExecutorService(new ScheduledThreadPoolExecutor(1, threadFactory));
    }

    /**
     * <p>
     * 用来处理延时任务或定时任务线程池
     * </p>
     * <p>
     * <pre>
     *     接收SchduledFutureTask类型的任务，有两种提交任务的方式：
     *     		scheduledAtFixedRate
     *     		scheduledWithFixedDelay
     *     SchduledFutureTask接收的参数：
     *     		time：任务开始的时间
     *     		sequenceNumber：任务的序号
     *     		period：任务执行的时间间隔
     *     它采用DelayQueue存储等待的任务
     *     DelayQueue内部封装了一个PriorityQueue，它会根据time的先后时间排序，若time相同则根据sequenceNumber排序；
     *     DelayQueue也是一个无界队列；
     *     工作线程的执行过程：工作线程会从DelayQueue取已经到期的任务去执行；
     *     执行结束后重新设置任务的到期时间，再次放回DelayQueue
     * </pre>
     *
     * @param corePoolSize
     * @return
     * @author daiqi
     * @创建时间 2018年5月7日 下午2:34:33
     */
    public static ExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }

    /**
     * <p>
     * 用来处理延时任务或定时任务线程池
     * </p>
     * <p>
     * <pre>
     *     接收SchduledFutureTask类型的任务，有两种提交任务的方式：
     *     		scheduledAtFixedRate
     *     		scheduledWithFixedDelay
     *     SchduledFutureTask接收的参数：
     *     		time：任务开始的时间
     *     		sequenceNumber：任务的序号
     *     		period：任务执行的时间间隔
     *     它采用DelayQueue存储等待的任务
     *     DelayQueue内部封装了一个PriorityQueue，它会根据time的先后时间排序，若time相同则根据sequenceNumber排序；
     *     DelayQueue也是一个无界队列；
     *     工作线程的执行过程：工作线程会从DelayQueue取已经到期的任务去执行；
     *     执行结束后重新设置任务的到期时间，再次放回DelayQueue
     * </pre>
     *
     * @param corePoolSize  : int 线程池的大小
     * @param threadFactory : ThreadFactory : 线程工厂
     * @return
     * @author daiqi
     * @创建时间 2018年5月7日 下午2:38:08
     */
    public static ExecutorService newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory) {
        return new ScheduledThreadPoolExecutor(corePoolSize, threadFactory);
    }

    /**
     * A wrapper class that exposes only the ExecutorService methods of an
     * ExecutorService implementation.
     */
    static class DelegatedExecutorService extends AbstractExecutorService {
        private final ExecutorService e;

        DelegatedExecutorService(ExecutorService executor) {
            e = executor;
        }

        @Override
        public void execute(Runnable command) {
            e.execute(command);
        }


        @Override
        public void shutdown() {
            e.shutdown();
        }

        @Override
        public List<Runnable> shutdownNow() {
            return e.shutdownNow();
        }

        @Override
        public boolean isShutdown() {
            return e.isShutdown();
        }

        @Override
        public boolean isTerminated() {
            return e.isTerminated();
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return e.awaitTermination(timeout, unit);
        }

        @Override
        public Future<?> submit(Runnable task) {
            return e.submit(task);
        }

        @Override
        public <T> Future<T> submit(Callable<T> task) {
            return e.submit(task);
        }

        @Override
        public <T> Future<T> submit(Runnable task, T result) {
            return e.submit(task, result);
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
            return e.invokeAll(tasks);
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
                throws InterruptedException {
            return e.invokeAll(tasks, timeout, unit);
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
                throws InterruptedException, ExecutionException {
            return e.invokeAny(tasks);
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
                throws InterruptedException, ExecutionException, TimeoutException {
            return e.invokeAny(tasks, timeout, unit);
        }
    }

    static class FinalizableDelegatedExecutorService extends DelegatedExecutorService {
        FinalizableDelegatedExecutorService(ExecutorService executor) {
            super(executor);
        }

        @Override
        protected void finalize() {
            super.shutdown();
        }
    }

    /**
     * A wrapper class that exposes only the ScheduledExecutorService methods of
     * a ScheduledExecutorService implementation.
     */
    static class DelegatedScheduledExecutorService extends DelegatedExecutorService
            implements ScheduledExecutorService {
        private final ScheduledExecutorService e;

        DelegatedScheduledExecutorService(ScheduledExecutorService executor) {
            super(executor);
            e = executor;
        }

        @Override
        public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
            return e.schedule(command, delay, unit);
        }

        @Override
        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
            return e.schedule(callable, delay, unit);
        }

        @Override
        public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
            return e.scheduleAtFixedRate(command, initialDelay, period, unit);
        }

        @Override
        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay,
                                                         TimeUnit unit) {
            return e.scheduleWithFixedDelay(command, initialDelay, delay, unit);
        }
    }

}

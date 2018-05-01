package com.easy.cloud.core.common.thread.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.thread.factory.EcThreadExecutors;

/**
 * 
 * <p>
 * 线程工具类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月27日 下午4:28:07
 */
public class EcThreadUtils {
	private static int count = Runtime.getRuntime().availableProcessors();
	static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(count);
	static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	static ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(count);
	static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
	static ExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
	static ExecutorService workStealingPool = Executors.newWorkStealingPool(count);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		testSubmitCachedThreadPool();
	}

	public static void testSubmitCachedThreadPool() throws InterruptedException, ExecutionException {
		// 进行异步任务列表
		List<FutureTask<Map<String, Object>>> futureTasks = new ArrayList<FutureTask<Map<String, Object>>>();
		// 线程池 初始化十个线程 和JDBC连接池是一个意思 实现重用
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		long start = System.currentTimeMillis();
		// 类似与run方法的实现 Callable是一个接口，在call中手写逻辑代码
		Callable<Map<String, Object>> callable = getCall();
		int count = 0;
		for (int i = 0; i < 10; i++) {
			// 创建一个异步任务
			FutureTask<Map<String, Object>> futureTask = new FutureTask<Map<String, Object>>(callable);
			futureTasks.add(futureTask);
			// 提交异步任务到线程池，让线程池管理任务 特爽把。
			// 由于是异步并行任务，所以这里并不会阻塞
			executorService.submit(futureTask);
			Map<String, Object> result = futureTask.get();
			count += EcMapUtils.getInteger(result, "result");
		}

//		int count = 0;
//		for (FutureTask<Map<String, Object>> futureTask : futureTasks) {
//			// futureTask.get() 得到我们想要的结果
//			// 该方法有一个重载get(long timeout, TimeUnit unit) 第一个参数为最大等待时间，第二个为时间的单位
//			Map<String, Object> result = futureTask.get();
//			count += EcMapUtils.getInteger(result, "result");
//		}
		long end = System.currentTimeMillis();
		System.out.println("线程池的任务全部完成:结果为:" + count + "，main线程关闭，进行线程的清理");
		System.out.println("使用时间：" + (end - start) + "ms");
		// 清理线程池
		executorService.shutdown();

	}
	public static Callable<Map<String, Object>> getCall() {
		return new Callable<Map<String, Object>>() {
			@Override
			public Map<String, Object> call() throws Exception {
				Integer res = new Random().nextInt(100);
				System.out.println("任务执行:获取到结果 :" + res);
				Map<String, Object> retMap = new HashMap<>();
				retMap.put("result", res);
				return retMap;
			}
		};
	}
	public static void testExecuteCachedThreadPool() {
		// 清理线程池
		ExecutorService executorService = EcThreadExecutors.newCachedThreadPool();
		for (int i = 0; i < 2; i++) {
			final int index = i;
			
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(index);
				}
			});
		}
		// 清理线程池
		executorService.shutdown();
	}
}

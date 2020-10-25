package com.sika.code.standard.footer.demo.common.mq.receiver;

import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author daiqi
 * @create 2020-06-13 21:38
 */
@Slf4j
public class Test {


    public static void oom1() throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100000000; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                }
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }


    @GetMapping("right")
    public int right() throws InterruptedException {
        //使用一个计数器跟踪完成的任务数
        AtomicInteger atomicInteger = new AtomicInteger();
        //创建一个具有2个核心线程、
        // 5个最大线程，
        // 使用容量为10的ArrayBlockingQueue阻塞队列作为工作队列的线程池
        // 使用默认的AbortPolicy拒绝策略
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2, 5,
                5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").get(),
                new ThreadPoolExecutor.AbortPolicy());

        //每隔1秒提交一次，一共提交20次任务
        IntStream.rangeClosed(1, 20).forEach(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int id = atomicInteger.incrementAndGet();
            try {
                threadPool.submit(() -> {
                    log.info("{} started", id);
                    //每个任务耗时10秒
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    log.info("{} finished", id);
                });
            } catch (Exception ex) {
                //提交出现异常的话，打印出错信息并为计数器减一
                log.error("error submitting task {}", id, ex);
                atomicInteger.decrementAndGet();
            }
        });

        TimeUnit.SECONDS.sleep(60);
        return atomicInteger.intValue();
    }

    public static void main(String[] args) {
        System.out.println("行1：" + (0.1+0.2));      // 1
        System.out.println("行2：" + (1.0-0.8));      // 2
        System.out.println("行3：" + (4.015*100));    // 3
        System.out.println("行4：" + (123.3/100));    // 4
        double amount1 = 2.15;
        double amount2 = 1.10;
        if (amount1 - amount2 == 1.05) {
            System.out.println("行5：OK");     // 5
        } else {
            System.out.println("行6: NO-OK");  // 6
        }


        System.out.println("BigDecimal行1：" + new BigDecimal(0.1).add(new BigDecimal(0.2)));
        System.out.println("BigDecimal行2：" + new BigDecimal(1.0).subtract(new BigDecimal(0.8)));
        System.out.println("BigDecimal行3：" + new BigDecimal(4.015).multiply(new BigDecimal(100)));
        System.out.println("BigDecimal行4：" + new BigDecimal(123.3).divide(new BigDecimal(100)));

        System.out.println("BigDecimal-Str行1：" + new BigDecimal("0.1").add(new BigDecimal("0.2")));
        System.out.println("BigDecimal-Str行2：" + new BigDecimal("1.0").subtract(new BigDecimal("0.8")));
        System.out.println("BigDecimal-Str行3：" + new BigDecimal("4.015").multiply(new BigDecimal("100")));
        System.out.println("BigDecimal-Str行4：" + new BigDecimal("123.3").divide(new BigDecimal("100")));

        System.out.println("BigDecimal-equals：" + new BigDecimal("4.015").multiply(new BigDecimal("100"))
                .equals(new BigDecimal("401.5")));
        System.out.println("BigDecimal-compareTo：" + new BigDecimal("4.015").multiply(new BigDecimal("100"))
                .compareTo(new BigDecimal("401.5")));
        System.out.println("BigDecimal[0.1+0.2]-equals：" + (new BigDecimal(0.1).add(new BigDecimal(0.2))
                .equals(new BigDecimal(0.3))));
        System.out.println("BigDecimal[0.1+0.2]-equals：" + (new BigDecimal(0.1).add(new BigDecimal(0.2))
                .compareTo(new BigDecimal(0.3))));
    }
}

class Data {
    @Getter
    private static int counter = 0;

    public static int reset() {
        counter = 0;
        return counter;
    }

    public synchronized void wrong() {
        counter++;
    }
}

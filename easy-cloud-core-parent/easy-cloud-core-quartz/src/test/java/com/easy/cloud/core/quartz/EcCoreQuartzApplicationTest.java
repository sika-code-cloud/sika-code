package com.easy.cloud.core.quartz;

import com.easy.cloud.EcCoreApplication;
import com.easy.cloud.core.quartz.job.SampleJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author daiqi
 * @create 2018-06-15 11:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EcCoreApplication.class) // 指定spring-boot的启动类
public class EcCoreQuartzApplicationTest {
    @Autowired
    private Scheduler scheduler;

    @Test
    public void test() throws Exception {

        JobDetail jobDetail = JobBuilder.newJob(SampleJob.class)
                .storeDurably(true)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startNow()
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        Thread.sleep(Integer.MAX_VALUE);
    }
}

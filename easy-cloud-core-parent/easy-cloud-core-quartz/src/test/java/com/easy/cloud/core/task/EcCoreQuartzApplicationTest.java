package com.easy.cloud.core.task;

import com.easy.cloud.EcCoreApplication;
import com.easy.cloud.core.task.job.EcRestJob;
import com.easy.cloud.core.task.scheduler.pojo.dto.EcTaskSchedulerDTO;
import com.easy.cloud.core.task.scheduler.service.EcTaskSchedulerService;
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
    @Autowired
    private EcTaskSchedulerService taskSchedulerService;

    @Test
    public void test() throws Exception {

        JobDetail jobDetail = JobBuilder.newJob(EcRestJob.class)
                .storeDurably(true)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startNow()
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testInitTask() throws InterruptedException {
        EcTaskSchedulerDTO taskSchedulerDTO = new EcTaskSchedulerDTO();
        taskSchedulerDTO.cronExpression("0/5 * * * * ? ")
                .jobGroup("jobGroup")
                .jobName("jobName")
                .triggerName("triggerName")
                .triggerGroup("triggerGroup");
        taskSchedulerService.initTask(taskSchedulerDTO);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testUpdateTaskScheduler() throws InterruptedException {
        EcTaskSchedulerDTO taskSchedulerDTO = new EcTaskSchedulerDTO();
        taskSchedulerDTO.cronExpression("0/30 * * * * ? ")
                .jobGroup("jobGroup")
                .jobName("jobName")
                .triggerName("triggerName")
                .triggerGroup("triggerGroup");
        taskSchedulerService.updateTaskScheduler(taskSchedulerDTO);
        Thread.sleep(Integer.MAX_VALUE);
    }
}

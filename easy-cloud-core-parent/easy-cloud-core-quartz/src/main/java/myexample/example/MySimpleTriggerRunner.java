package myexample.example;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author daiqi
 * @create 2018-06-14 14:51
 */
public class MySimpleTriggerRunner {
    public static void main(String[] args) throws SchedulerException {
        // 构建任务
        JobDetail jobDetail = JobBuilder.newJob(MySimpleJob.class)
                .withIdentity("job1", "group1")
                .build();

        Date beginDate = DateBuilder.futureDate(15, DateBuilder.IntervalUnit.SECOND);
        Date endDate = DateBuilder.futureDate(60,  DateBuilder.IntervalUnit.SECOND);
        // 构建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "tgroup")
                .startAt(beginDate)
                .endAt(endDate)
                .withSchedule(
                        SimpleScheduleBuilder.repeatSecondlyForever()
                )
                .build();

        // 执行计划工厂
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        // 获取执行计划
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 将任务触发器放入设置到执行计划中
        scheduler.scheduleJob(jobDetail, trigger);
        // 开始
        scheduler.start();

    }
}

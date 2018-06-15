package com.easy.cloud.core.quartz.config;

import com.easy.cloud.core.quartz.job.SampleJob;
import com.easy.cloud.core.quartz.spring.AutowiringSpringBeanJobFactory;
import org.quartz.*;
import org.quartz.impl.calendar.AnnualCalendar;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfig {

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public Scheduler schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory,
                                          @Qualifier("cronJobTrigger") Trigger cronJobTrigger) throws Exception {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // this allows to update triggers in DB when updating settings in config file:
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);

        factory.setQuartzProperties(quartzProperties());
        factory.afterPropertiesSet();

        Scheduler scheduler = factory.getScheduler();
//        scheduler.addCalendar("holidays1", getAnnualCalendar(), false, false);

        scheduler.setJobFactory(jobFactory);
        scheduler.scheduleJob(cronJobDetail(), cronJobTrigger);

        scheduler.start();
        return scheduler;
    }

    private AnnualCalendar getAnnualCalendar() {
        AnnualCalendar holidays = new AnnualCalendar();
        Calendar calendar = new GregorianCalendar(2018, 5, 15);
        holidays.setDayExcluded(calendar, true);
        return holidays;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

//    @Bean
//    public JobDetailFactoryBean cronJobDetail() {
//        return createJobDetail(SampleJob.class, "cron");
//    }

    @Bean
    public JobDetail cronJobDetail() {
        return JobBuilder.newJob(SampleJob.class)
                .withIdentity("test_cron1", "test_cron_group1")
                .build();
    }

    @Bean(name = "cronJobTrigger")
    public Trigger cronJobTrigger(@Value("${cronjob.cronExpression}") String cronExpression) {
        return TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger1", "cronTriggerGroup1")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .modifiedByCalendar("holidays")
                .build();
    }

//    @Bean(name = "cronJobTrigger")
//    public CronTriggerFactoryBean cronJobTrigger(@Qualifier("cronJobDetail") JobDetail jobDetail,
//                                                 @Value("${cronjob.cronExpression}") String cronExpression) {
//        return createCronTrigger(jobDetail, cronExpression);
//    }

    private static JobDetailFactoryBean createJobDetail(Class jobClass, String name) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setName(name);
        // job has to be durable to be stored in DB:
        factoryBean.setDurability(true);
        return factoryBean;
    }

    private static SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail, long pollFrequencyMs) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setStartDelay(0L);
        factoryBean.setRepeatInterval(pollFrequencyMs);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        // in case of misfire, ignore all missed triggers and continue :
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
        return factoryBean;
    }

    private static CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronExpression) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        return factoryBean;
    }

}
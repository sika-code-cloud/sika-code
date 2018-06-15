package com.easy.cloud.core.quartz.config;

import com.easy.cloud.core.quartz.job.SampleJob;
import com.easy.cloud.core.quartz.job.SampleJob2;
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
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

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
//    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean, @Qualifier("cronJobTrigger") Trigger cronJobTrigger) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
//        scheduler.addCalendar("holidays", getAnnualCalendar(), false, false);

        scheduler.scheduleJob(cronJobDetail(), cronJobTrigger);

        scheduler.start();
        return scheduler;
    }

    @Bean(name = "schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory) throws Exception {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        factory.afterPropertiesSet();
        factory.setStartupDelay(10);
        factory.setAutoStartup(true);
        return factory;
    }

    private AnnualCalendar getAnnualCalendar() {
        AnnualCalendar holidays = new AnnualCalendar();
        Calendar calendar = new GregorianCalendar(2018, 5, 11);
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


    @Bean
    public JobDetail cronJobDetail() {
        return JobBuilder.newJob(SampleJob.class)
                .withIdentity("test_cron１", "test_cron_group１")
                .build();
    }

    @Bean(name = "cronJobTrigger")
    public Trigger cronJobTrigger(@Value("${cronjob.cronExpression}") String cronExpression) {
        return TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger3", "cronTriggerGroup3")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
//                .modifiedByCalendar("holidays")
                .build();
    }

}
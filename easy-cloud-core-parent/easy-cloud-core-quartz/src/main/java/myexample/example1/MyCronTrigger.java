package myexample.example1;

import org.quartz.*;

import java.util.ArrayList;
import java.util.Calendar;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

import java.util.GregorianCalendar;
import java.util.List;


/**
 * @author daiqi
 * @create 2018-06-14 16:47
 */
public class MyCronTrigger {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        AnnualCalendar holidays = new AnnualCalendar();
        Calendar calendar = new GregorianCalendar(2018, 5, 13);

        holidays.setDayExcluded(calendar, true);

        scheduler.addCalendar("holidays", holidays, false, false);


        JobDetail jobDetail = JobBuilder.newJob(MyCronJob.class)
                .withIdentity("cron", "crongroup")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger", "cronTriggerGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? "))
                .modifiedByCalendar("holidays")
                .build();


        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}

package com.easy.cloud.core.task.myexample.exampledb;

import com.easy.cloud.core.task.examples.example13.SimpleRecoveryStatefulJob;
import com.easy.cloud.core.task.examples.example15.ClusterExample;
import com.easy.cloud.core.task.examples.example15.SimpleRecoveryJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.DateBuilder.IntervalUnit;
import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author daiqi
 * @create 2018-06-14 19:23
 */
public class JDBCJobStoreRunner {

    private static Logger _log = LoggerFactory.getLogger(ClusterExample.class);

    public void run(boolean inClearJobs, boolean inScheduleJobs) throws Exception {

        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        if (inClearJobs) {
            _log.warn("***** Deleting existing jobs/triggers *****");
            sched.clear();
        }

        _log.info("------- Initialization Complete -----------");

        if (inScheduleJobs) {

            _log.info("------- Scheduling Jobs ------------------");

            String schedId = sched.getSchedulerInstanceId();

            int count = 2;

            JobDetail job = newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId) // put triggers in group
                    // named after the cluster
                    // node instance just to
                    // distinguish (in logging)
                    // what was scheduled from
                    // where
                    .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
                    // down...
                    .build();

            SimpleTrigger trigger = newTrigger().withIdentity("triger_" + count, schedId)
                    .startAt(futureDate(1, IntervalUnit.SECOND))
                    .withSchedule(simpleSchedule().withRepeatCount(20).withIntervalInSeconds(5)).build();

            _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: "
                    + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
            sched.scheduleJob(job, trigger);

            count++;

            job = newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId) // put triggers in group named after
                    // the cluster node instance just to
                    // distinguish (in logging) what was
                    // scheduled from where
                    .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
                    // down...
                    .build();

            trigger = newTrigger().withIdentity("triger_" + count, schedId).startAt(futureDate(2, IntervalUnit.SECOND))
                    .withSchedule(simpleSchedule().withRepeatCount(20).withIntervalInSeconds(5)).build();

            _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: "
                    + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
            sched.scheduleJob(job, trigger);

            count++;

            job = newJob(SimpleRecoveryStatefulJob.class).withIdentity("job_" + count, schedId) // put triggers in group named
                    // after the cluster node
                    // instance just to
                    // distinguish (in logging)
                    // what was scheduled from
                    // where
                    .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
                    // down...
                    .build();

            trigger = newTrigger().withIdentity("triger_" + count, schedId).startAt(futureDate(1, IntervalUnit.SECOND))
                    .withSchedule(simpleSchedule().withRepeatCount(20).withIntervalInSeconds(3)).build();

            _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: "
                    + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
            sched.scheduleJob(job, trigger);

            count++;

            job = newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId) // put triggers in group named after
                    // the cluster node instance just to
                    // distinguish (in logging) what was
                    // scheduled from where
                    .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
                    // down...
                    .build();

            trigger = newTrigger().withIdentity("triger_" + count, schedId).startAt(futureDate(1, IntervalUnit.SECOND))
                    .withSchedule(simpleSchedule().withRepeatCount(20).withIntervalInSeconds(4)).build();

            _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " & repeat: " + trigger.getRepeatCount()
                    + "/" + trigger.getRepeatInterval());
            sched.scheduleJob(job, trigger);

            count++;

            job = newJob(SimpleRecoveryJob.class).withIdentity("job_" + count, schedId) // put triggers in group named after
                    // the cluster node instance just to
                    // distinguish (in logging) what was
                    // scheduled from where
                    .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
                    // down...
                    .build();

            trigger = newTrigger().withIdentity("triger_" + count, schedId).startAt(futureDate(1, IntervalUnit.SECOND))
                    .withSchedule(simpleSchedule().withRepeatCount(20).withIntervalInMilliseconds(4500L)).build();

            _log.info(job.getKey() + " will run at: " + trigger.getNextFireTime() + " & repeat: " + trigger.getRepeatCount()
                    + "/" + trigger.getRepeatInterval());
            sched.scheduleJob(job, trigger);
        }

        // jobs don't start firing until start() has been called...
        _log.info("------- Starting Scheduler ---------------");
        sched.start();
        _log.info("------- Started Scheduler ----------------");

        _log.info("------- Waiting for one hour... ----------");
        try {
            Thread.sleep(3600L * 1000L);
        } catch (Exception e) {
            //
        }

        _log.info("------- Shutting Down --------------------");
        sched.shutdown();
        _log.info("------- Shutdown Complete ----------------");
    }

    public static void main(String[] args) throws Exception {
        boolean clearJobs = false;
        boolean scheduleJobs = true;

        for (String arg : args) {
            if (arg.equalsIgnoreCase("clearJobs")) {
                clearJobs = true;
            } else if (arg.equalsIgnoreCase("dontScheduleJobs")) {
                scheduleJobs = false;
            }
        }

        ClusterExample example = new ClusterExample();
        example.run(clearJobs, scheduleJobs);
    }
}

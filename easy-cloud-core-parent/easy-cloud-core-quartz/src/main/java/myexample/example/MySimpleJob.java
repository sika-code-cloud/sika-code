package myexample.example;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author daiqi
 * @create 2018-06-14 14:47
 */
public class MySimpleJob implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(context.getTrigger().getKey() + "triggered.time is : " + (new Date()));
    }


}

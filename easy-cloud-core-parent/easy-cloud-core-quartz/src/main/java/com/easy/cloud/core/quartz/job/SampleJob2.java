package com.easy.cloud.core.quartz.job;

import com.easy.cloud.core.quartz.service.SampleService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by david on 2015-01-20.
 */
public class SampleJob2 implements Job {
    @Autowired
    private SampleService service;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        logger.info(jobExecutionContext.getJobDetail().getKey() +"=================第二个任务哦哦");
    }
}
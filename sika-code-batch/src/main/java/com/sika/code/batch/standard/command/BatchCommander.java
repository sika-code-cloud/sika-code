package com.sika.code.batch.standard.command;

import cn.hutool.core.util.IdUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/11 10:54
 */
@Component
public class BatchCommander {
    @Autowired
    private SimpleJobLauncher jobLauncher;
    @Autowired
    private JobRepository jobRepository;
    private static final String JOB_DATE = "jobDate";
    private static final String JOB_ID = "jobId";

    /**
     * @param job           : 执行的JOB
     * @param jobParameters : JOB参数
     */
    public void execute(Job job, JobParameters jobParameters) {
        try {
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void execute(Job job) {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate(JOB_DATE, new Date());
        jobParametersBuilder.addString(JOB_ID, IdUtil.objectId());
        execute(job, jobParametersBuilder.toJobParameters());
    }
}

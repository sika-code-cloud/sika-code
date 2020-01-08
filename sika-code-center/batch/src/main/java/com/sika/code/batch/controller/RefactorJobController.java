package com.sika.code.batch.controller;

import com.sika.code.common.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
public class RefactorJobController {

    @Autowired
    SimpleJobLauncher jobLauncher;
    @Autowired
    private Job refactorJob1;
    @Autowired
    private Job refactorJob2;
    @Autowired
    private Job refactorJob3;


    @RequestMapping(value = "testRefactorJob1")
    public void testRefactorJob1() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters();

        try {
            Job refactorJob1 = SpringUtil.getBean("refactorJob1", Job.class);
            jobLauncher.run(refactorJob1, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "testRefactorJob2")
    public void testRefactorJob2() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters();

        try {
            jobLauncher.run(refactorJob2, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "testRefactorJob3")
    public void testRefactorJob3() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters();
        try {
            Job refactorJob3 = SpringUtil.getBean("refactorJob3", Job.class);
            jobLauncher.run(refactorJob3, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
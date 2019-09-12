package com.sika.code.batch.controller;

import com.sika.code.batch.animal.mapper.AnimalMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    SimpleJobLauncher jobLauncher;

    @Autowired
    Job importJob;
    @Autowired
    Job importJob1;

    @Autowired
    private AnimalMapper animalMapper;

    public JobParameters jobParameters;

    @RequestMapping("/test")
    public void imp() throws Exception {
        jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(importJob, jobParameters);
    }

    @RequestMapping("/test1")
    public void imp1() throws Exception {
        jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(importJob1, jobParameters);
    }
}
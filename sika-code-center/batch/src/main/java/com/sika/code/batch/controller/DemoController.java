package com.sika.code.batch.controller;

import com.google.common.collect.Lists;
import com.sika.code.batch.adaptor.JobParametersBuilderExp;
import com.sika.code.batch.dto.JobParametersData;
import com.sika.code.batch.test.animal.mapper.AnimalMapper;
import com.sika.code.batch.test.person.PersonEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    SimpleJobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "importJob")
    private Job importJob;

    @Autowired
    @Qualifier(value = "importJob1")
    private Job importJob1;

    @Autowired
    @Qualifier(value = "partitionJob")
    private Job partitionJob;
    @Autowired
    private AnimalMapper animalMapper;

    public JobParameters jobParameters;

    @RequestMapping("/test")
    public void imp() throws Exception {
        List<String> names = Lists.newArrayList("name", "age", "nation", "address");
        JobParametersData jobParametersData = new JobParametersData()
                .setDelimiter("|")
                .setNames(names)
                .setResource(new ClassPathResource("person.csv"))
                .setFromClass(PersonEntity.class)
                .setToClass(PersonEntity.class)
                ;
        JobParameters jobParameters = JobParametersBuilderExp.build(jobParametersData);
        jobLauncher.run(importJob, jobParameters);
    }

    @RequestMapping("/test1")
    public void imp1() throws Exception {
        jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("path", "E:\\Users\\animal.csv")
                .toJobParameters();
        jobLauncher.run(importJob1, jobParameters);
    }

    @RequestMapping("/test2")
    public void partitionJob() throws Exception {
        jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(partitionJob, jobParameters);
    }
}
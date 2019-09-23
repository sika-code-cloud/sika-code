package com.sika.code.batch.controller;

import com.alibaba.fastjson.JSONArray;
import com.sika.code.batch.animal.mapper.AnimalMapper;
import com.sika.code.common.json.util.JSONUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        JobParameters jobParameters;
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("name");
        jsonArray.add("age");
        jsonArray.add("nation");
        jsonArray.add("address");
        jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("namestr", JSONArray.toJSONString(jsonArray))
                .toJobParameters();
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
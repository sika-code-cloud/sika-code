package com.sika.code.batch.controller;

import com.google.common.collect.Lists;
import com.sika.code.batch.adaptor.JobParametersBuilderExp;
import com.sika.code.batch.dto.JobParametersData;
import com.sika.code.batch.dto.StepData;
import com.sika.code.batch.factory.ItemProcessFactory;
import com.sika.code.batch.factory.ItemReaderFactory;
import com.sika.code.batch.factory.ItemWriterFactory;
import com.sika.code.batch.test.animal.AnimalDTO;
import com.sika.code.batch.test.animal.AnimalEntity;
import com.sika.code.batch.test.animal.AnimalItemProcessor;
import com.sika.code.batch.test.animal.config.AnimalConfig;
import com.sika.code.batch.test.animal.mapper.AnimalMapper;
import com.sika.code.batch.test.animal.service.AnimalService;
import com.sika.code.batch.test.person.CsvItemProcessor;
import com.sika.code.batch.test.person.PersonEntity;
import com.sika.code.batch.util.BatchUtil;
import com.sika.code.common.json.util.JSONUtil;
import com.sika.code.common.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
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
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public JobParameters jobParameters;

    @RequestMapping("/test")
    public void imp() throws Exception {
        List<String> names = Lists.newArrayList("name", "age", "nation", "address");
        JobParametersData<PersonEntity, PersonEntity> jobParametersData = new JobParametersData();
        // csvItemReader
        FlatFileItemReader<PersonEntity> flatFileItemReader = ItemReaderFactory.newItemReader("csvItemReader", FlatFileItemReader.class);
        flatFileItemReader.setLineMapper(BatchUtil.lineMapper(PersonEntity.class, "|", names));
        flatFileItemReader.setResource(new ClassPathResource("person.csv"));
        // write
        ItemWriter<PersonEntity> itemWriter = ItemWriterFactory.newItemWriter("csvItemWrite", ItemWriter.class);
        // csvItemProcessor
        ItemProcessor<PersonEntity, PersonEntity> itemProcessor = ItemProcessFactory.newItemProcess("csvItemProcessor", CsvItemProcessor.class);

        StepData<PersonEntity, PersonEntity> stepData = new StepData<PersonEntity, PersonEntity>().build();

        jobParametersData.setItemReader(flatFileItemReader)
                .setItemWriter(itemWriter)
                .setItemProcessor(itemProcessor)
                .setStepData(stepData)
                .build();
        JobParameters jobParameters = JobParametersBuilderExp.build(jobParametersData);
        jobLauncher.run(importJob1, jobParameters);
    }

    @RequestMapping("/test1")
    public void imp1() throws Exception {
        List<String> names = Lists.newArrayList("name", "color");
        JobParametersData<AnimalDTO, AnimalEntity> jobParametersData = new JobParametersData();
        // csvItemReader
        FlatFileItemReader<AnimalDTO> flatFileItemReader = ItemReaderFactory.newItemReader("readerAnimal", FlatFileItemReader.class);
        flatFileItemReader.setLineMapper(BatchUtil.lineMapper(AnimalDTO.class, "|", names));
        flatFileItemReader.setResource(new ClassPathResource("animal.csv"));
        // write
        MyBatisBatchItemWriter<AnimalEntity> myBatisBatchItemWriter = ItemWriterFactory.newItemWriter("writerAnimal", MyBatisBatchItemWriter.class);
        myBatisBatchItemWriter.setStatementId(AnimalMapper.class.getName() + ".insert");
        // csvItemProcessor
        AnimalItemProcessor animalItemProcessor = ItemProcessFactory.newItemProcess("processorAnimal", AnimalItemProcessor.class);

        StepData<AnimalDTO, AnimalEntity> stepData = new StepData<AnimalDTO, AnimalEntity>().build();

        jobParametersData.setItemReader(flatFileItemReader)
                .setItemWriter(myBatisBatchItemWriter)
                .setItemProcessor(animalItemProcessor)
                .setStepData(stepData)
                .build();

        JobParameters jobParameters = JobParametersBuilderExp.build(jobParametersData);
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
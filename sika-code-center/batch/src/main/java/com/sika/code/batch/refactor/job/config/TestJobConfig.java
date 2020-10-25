package com.sika.code.batch.refactor.job.config;

import com.sika.code.batch.adaptor.ItemWriterAdapter;
import com.sika.code.batch.refactor.writer.MybatisWriterAdapter;
import com.sika.code.batch.service.AnimalService;
import com.sika.code.batch.strategy.names.NamesStrategy;
import com.sika.code.batch.strategy.names.impl.ResourceNamesStrategy;
import com.sika.code.batch.test.animal.AnimalDTO;
import com.sika.code.batch.test.animal.AnimalEntity;
import com.sika.code.batch.test.animal.AnimalItemProcessor;
import com.sika.code.batch.util.BatchUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Collection;


/**
 * @author daiqi
 * @create 2020-01-08 23:32
 */
@Configuration
@EnableBatchProcessing
@Slf4j
public class TestJobConfig {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;


    /**
     *
     * 1:使用PrototypeScope注解意味着每次创建对象都是新对象，从而避免使用JobScope和StepScope，从而保证其线程安全
     * 2:但是需要注意的是需要使用getApplicationContext().getBean()方式获取Bean不能通过注解的形式直接注入，否则将不会生效
     */
    @Bean
    @PrototypeScope
    public Job refactorJob1() {
        return jobBuilderFactory.get("refactorJob1")
                .start(refactorStep1())
                .build();
    }

    @Bean
    @PrototypeScope
    public Job refactorJob2() {
        return jobBuilderFactory.get("refactorJob2")
                .start(refactorStep1())
                .next(refactorStep2())
                .build();
    }
    @Bean
    @PrototypeScope
    public Job refactorJob3() {
        return jobBuilderFactory.get("refactorJob3")
                .start(refactorStep1())
                .next(refactorStep2())
                .next(refactorStep3())
                .next(refactorStepChunk1())
                .build();
    }
    @Bean
    @PrototypeScope
    public Step refactorStep1() {
        return stepBuilderFactory.get("refactorStep1").tasklet((contribution, chunkContext) -> {
            log.info("refactorStep1");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    @PrototypeScope
    public Step refactorStep2() {
        return stepBuilderFactory.get("refactorStep2").tasklet((contribution, chunkContext) -> {
            log.info("refactorStep2");
            return RepeatStatus.FINISHED;
        }).build();
    }


    @Bean
    @PrototypeScope
    public Step refactorStep3() {
        return stepBuilderFactory.get("refactorStep3").tasklet((contribution, chunkContext) -> {
            log.info("refactorStep3");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    @PrototypeScope
    public Step refactorStepChunk1() {
        return stepBuilderFactory.get("refactorStep3")
                .<AnimalDTO, AnimalEntity>chunk(10)
                .reader(refactorItemReader())
                .processor(refactorItemProcessor())
                .writer(refactorItemWriter())
                .build();
    }

    @Bean
    @PrototypeScope
    public ItemReader<AnimalDTO> refactorItemReader() {
        Resource resource = new ClassPathResource("animal.csv");
        String delimiter = "|";
        NamesStrategy namesStrategy = new ResourceNamesStrategy()
                .setResource(resource)
                .setDelimiter(delimiter)
                .build();


        FlatFileItemReader<AnimalDTO> itemReader = new FlatFileItemReaderBuilder<AnimalDTO>()
                .name("refactorItemReader")
                .resource(resource)
                .lineMapper(BatchUtil.buildLineMapper(AnimalDTO.class, delimiter, namesStrategy))
                .linesToSkip(1)
                .build();

        return itemReader;
    }

    @Bean
    @PrototypeScope
    public ItemProcessor<AnimalDTO, AnimalEntity> refactorItemProcessor() {
        return new AnimalItemProcessor();
    }

//    @Bean
//    @PrototypeScope
//    public ItemWriter<AnimalEntity> refactorItemWriter() {
////        MyBatisBatchItemWriter myBatisBatchItemWriter = new MyBatisBatchItemWriterBuilder()
////                .statementId(BatchUtil.buildStatementId(AnimalMapper.class, "insert"))
////                .sqlSessionFactory(sqlSessionFactory)
////                .build();
////        return myBatisBatchItemWriter;
////
//        ItemWriter<AnimalEntity> writer = new MybatisWriterAdapter<AnimalEntity>()
//                .setTargetObjectClass(AnimalService.class)
//                .setTargetMethod("saveBatch")
//                .setParamTypes(new Class[]{Collection.class})
//                .build();
//        return writer;
//    }

    @Bean
    @PrototypeScope
    public ItemWriter<AnimalEntity> refactorItemWriter() {
//
        ItemWriterAdapter<AnimalEntity> writer = new ItemWriterAdapter<>();
        writer.setTargetObjectClass(AnimalService.class);
        writer.setTargetMethod("saveBatch");
        writer.setArguments(new Class[]{Collection.class});
        return writer.build();
    }
}

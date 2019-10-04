package com.sika.code.batch.test.animal.config;

import com.sika.code.batch.adaptor.JobParametersBuilderExp;
import com.sika.code.batch.dto.JobParametersData;
import com.sika.code.batch.dto.StepCommonData;
import com.sika.code.batch.dto.StepData;
import com.sika.code.batch.dto.StepExceptionData;
import com.sika.code.batch.test.animal.AnimalItemProcessor;
import com.sika.code.batch.test.animal.AnimalValidator;
import com.sika.code.batch.test.animal.CsvJobListener1;
import com.sika.code.batch.test.animal.listen.AnimalFailureLoggerListener;
import com.sika.code.common.string.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

/**
 * @author daiqi
 * @create 2019-09-12 0:12
 */
@Configuration
@EnableBatchProcessing
@Slf4j
public class AnimalConfig {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    /**
     * ItemReader定义,用来读取数据
     * 1，使用FlatFileItemReader读取文件
     * 2，使用FlatFileItemReader的setResource方法设置csv文件的路径
     * 3，对此对cvs文件的数据和领域模型类做对应映射
     *
     * @return
     * @throws Exception
     */
    @Bean
    @StepScope
    @Scope(value = "prototype")
    public FlatFileItemReader readerAnimal() {
        FlatFileItemReader flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setLineMapper(new DefaultLineMapper());
        return flatFileItemReader;
    }


    /**
     * ItemProcessor定义，用来处理数据
     *
     * @return
     */
    @Bean
    @StepScope
    @Scope(value = "prototype")
    public ItemProcessor processorAnimal() {
        //使用我们自定义的ItemProcessor的实现CsvItemProcessor
        AnimalItemProcessor processor = new AnimalItemProcessor();
        //为processor指定校验器为CsvBeanValidator()
        processor.setValidator(animalValidator());
        return processor;
    }

    /**
     * ItemWriter定义，用来输出数据
     * spring能让容器中已有的Bean以参数的形式注入，Spring Boot已经为我们定义了dataSource
     *
     * @param dataSource
     * @return
     */
//    @Bean
//    public ItemWriter<AnimalDTO> writerAnimal(@Qualifier("dataSource") DataSource dataSource) {
//        JdbcBatchItemWriter<AnimalDTO> csvItemWrite = new JdbcBatchItemWriter<>();
//        //我们使用JDBC批处理的JdbcBatchItemWriter来写数据到数据库
//        csvItemWrite.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//        String sql = "insert into animal " + " (name,color) "
//                + " values(:name,:color)";
//        //在此设置要执行批处理的SQL语句
//        csvItemWrite.setSql(sql);
//        csvItemWrite.setDataSource(dataSource);
//        return csvItemWrite;
//    }
    @Bean
    @StepScope
    @Scope(value = "prototype")
    public ItemWriter writerAnimal() {
        MyBatisBatchItemWriter myBatisBatchItemWriter = new MyBatisBatchItemWriter<>();
        myBatisBatchItemWriter.setSqlSessionFactory(sqlSessionFactory);
        myBatisBatchItemWriter.setStatementId(StringUtil.EMPTY);
        return myBatisBatchItemWriter;
    }
    /**
     * step步骤，包含ItemReader，ItemProcessor和ItemWriter
     *
     * @return
     */
    @Bean
    @JobScope
    public Step step1() {
        JobParametersData jobParametersData = JobParametersBuilderExp.fromData();
        ItemWriter itemWriter = jobParametersData.getItemWriter();
        ItemReader itemReader = jobParametersData.getItemReader();
        ItemProcessor itemProcessor = jobParametersData.getItemProcessor();
        StepData stepData = jobParametersData.getStepData();
        StepCommonData stepCommonData = stepData.getStepCommonData();
        StepExceptionData stepExceptionData = stepData.getStepExceptionData();

        SimpleStepBuilder builder = (SimpleStepBuilder) stepBuilderFactory
                .get(stepCommonData.getName())
                .chunk(stepCommonData.getChunk())
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .faultTolerant()
                .skipLimit(stepCommonData.getSkipLimit())
                .skip(stepCommonData.getSkipException())
                .retryLimit(stepCommonData.getRetryLimit())
                .retry(stepCommonData.getRetryException())

                .listener(stepExceptionData.getSkipListener())
                .listener(stepExceptionData.getItemWriteListener())
                .listener(stepExceptionData.getItemReadListener())
                .listener(stepExceptionData.getItemProcessListener())
                .listener(stepExceptionData.getChunkListener())
                .listener(stepExceptionData.getStepExecutionListener());
        return builder.build();
//        return builder1.build();
//
//        SimpleStepBuilder builder = (SimpleStepBuilder) stepBuilderFactory
//                .get("step1")
//                .chunk(1000)//批处理每次提交65000条数据
//                .csvItemReader(readerAnimal)//给step绑定reader
//                .csvItemProcessor(processorAnimal)//给step绑定processor
//                .csvItemWrite(writerAnimal)//给step绑定writer
//                .faultTolerant()
//                .skipLimit(10)
//                .skip(Exception.class)
//                .retryLimit(5)
//                .retry(ServiceUnavailableException.class)
//                .listener(new AnimalSkipListener())
//                .listener(new AnimalItemWriteListener())
//                .listener(new AnimalItemReadListener())
//                .listener(new AnimalItemProcessListener())
//                .listener(new AnimalChunkListener())
//                .listener(new AnimalStepExecutionListener());

//        return builder.build();
    }

    /**
     * Job定义，我们要实际执行的任务，包含一个或多个Step
     *
     * @param jobBuilderFactory
     * @param s1
     * @return
     */
    @Bean
    public Job importJob1(Step step1) {
        return jobBuilderFactory.get("importJob1")
                .incrementer(new RunIdIncrementer())
                .start(step1)//为Job指定Step
                .validator(parameters -> log.info("------------parameters-------------{}", parameters))
                .listener(csvJobListener1())//绑定监听器csvJobListener
                .build();
    }


    @Bean
    public AnimalFailureLoggerListener animalFailureLoggerListener() {
        return new AnimalFailureLoggerListener();
    }

    @Bean
    public CsvJobListener1 csvJobListener1() {
        return new CsvJobListener1();
    }

    @Bean
    public AnimalValidator animalValidator() {
        return new AnimalValidator();
    }

}

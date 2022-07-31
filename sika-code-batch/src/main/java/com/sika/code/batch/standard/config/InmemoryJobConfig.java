package com.sika.code.batch.standard.config;

import com.sika.code.batch.standard.store.StandardMapJobRepositoryFactoryBean;
import org.springframework.batch.core.configuration.ListableJobLocator;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.SimpleJobExplorer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SyncTaskExecutor;

@Configuration
public class InmemoryJobConfig {


    @Bean
    public ListableJobLocator listableJobLocator() {
        return new MapJobRegistry();
    }

    @Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }


    @Bean
    public StandardMapJobRepositoryFactoryBean mapJobRepositoryFactoryBean(ResourcelessTransactionManager resourcelessTransactionManager) throws Exception {
        StandardMapJobRepositoryFactoryBean factoryBean = new StandardMapJobRepositoryFactoryBean(resourcelessTransactionManager);
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

    @Bean
    public JobRepository jobRepository(StandardMapJobRepositoryFactoryBean repositoryFactory) throws Exception {
        return new SimpleJobRepository(repositoryFactory.getJobInstanceDao(), repositoryFactory.getJobExecutionDao(),
                repositoryFactory.getStepExecutionDao(), repositoryFactory.getExecutionContextDao());
    }

    @Bean
    public StepBuilderFactory stepBuilderFactory(JobRepository jobRepository, ResourcelessTransactionManager resourcelessTransactionManager) {
        return new StepBuilderFactory(jobRepository, resourcelessTransactionManager);
    }

    @Bean
    public JobBuilderFactory jobBuilderFactory(JobRepository jobRepository) {
        return new JobBuilderFactory(jobRepository);
    }

    @Bean
    public JobExplorer jobExplorer(StandardMapJobRepositoryFactoryBean repositoryFactory) {
        return new SimpleJobExplorer(repositoryFactory.getJobInstanceDao(), repositoryFactory.getJobExecutionDao(),
                repositoryFactory.getStepExecutionDao(), repositoryFactory.getExecutionContextDao());
    }

    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        simpleJobLauncher.setTaskExecutor(new SyncTaskExecutor());

        return simpleJobLauncher;
    }
}
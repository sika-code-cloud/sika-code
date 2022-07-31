package com.sika.code.batch.standard.store;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.repository.dao.ExecutionContextDao;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.batch.core.repository.dao.JobInstanceDao;
import org.springframework.batch.core.repository.dao.StepExecutionDao;
import org.springframework.batch.core.repository.support.AbstractJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/4 11:20
 */
public class StandardMapJobRepositoryFactoryBean extends AbstractJobRepositoryFactoryBean {

    private StandardMapJobExecutionDao jobExecutionDao;

    private StandardMapJobInstanceDao jobInstanceDao;

    private StandardMapStepExecutionDao stepExecutionDao;

    private StandardMapExecutionContextDao executionContextDao;

    /**
     * Create a new instance with a {@link ResourcelessTransactionManager}.
     */
    public StandardMapJobRepositoryFactoryBean() {
        this(new ResourcelessTransactionManager());
    }

    /**
     * Create a new instance with the provided transaction manager.
     *
     * @param transactionManager {@link PlatformTransactionManager}
     */
    public StandardMapJobRepositoryFactoryBean(PlatformTransactionManager transactionManager) {
        setTransactionManager(transactionManager);
    }

    public StandardMapJobExecutionDao getJobExecutionDao() {
        return jobExecutionDao;
    }

    public StandardMapJobInstanceDao getJobInstanceDao() {
        return jobInstanceDao;
    }

    public StandardMapStepExecutionDao getStepExecutionDao() {
        return stepExecutionDao;
    }

    public StandardMapExecutionContextDao getExecutionContextDao() {
        return executionContextDao;
    }

    /**
     * Convenience method to clear all the map DAOs globally, removing all
     * entities.
     */
    public void clear() {
        jobInstanceDao.clear();
        jobExecutionDao.clear();
        stepExecutionDao.clear();
        executionContextDao.clear();
    }

    public void clear(JobExecution jobExecution) {
        executionContextDao.clearExecutionContextDao(jobExecution);
        stepExecutionDao.clearStepExecutionDao(jobExecution);
        jobExecutionDao.clearJobExecution(jobExecution);
        jobInstanceDao.clearJobInstance(jobExecution);
    }

    @Override
    protected JobExecutionDao createJobExecutionDao() throws Exception {
        jobExecutionDao = new StandardMapJobExecutionDao();
        return jobExecutionDao;
    }

    @Override
    protected JobInstanceDao createJobInstanceDao() throws Exception {
        jobInstanceDao = new StandardMapJobInstanceDao();
        return jobInstanceDao;
    }

    @Override
    protected StepExecutionDao createStepExecutionDao() throws Exception {
        stepExecutionDao = new StandardMapStepExecutionDao();
        return stepExecutionDao;
    }

    @Override
    protected ExecutionContextDao createExecutionContextDao() throws Exception {
        executionContextDao = new StandardMapExecutionContextDao();
        return executionContextDao;
    }
}

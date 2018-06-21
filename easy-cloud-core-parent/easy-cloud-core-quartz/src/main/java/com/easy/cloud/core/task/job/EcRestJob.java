package com.easy.cloud.core.task.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用rest的方式处理job
 *
 * @author daiqi
 * @create 2018-06-20 19:56
 */
public class EcRestJob extends EcBaseJob {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void executeJob(JobExecutionContext executionContext) {
        logger.info(executionContext.getTrigger().getKey() + "=================rest任务哦");
    }

    @Override
    protected void executeJobBefore(JobExecutionContext executionContext) {
        logger.info("\r\n");
        logger.info(executionContext.getTrigger().getKey() + "=================rest任务之前");
    }

    @Override
    protected void executeJobAfter(JobExecutionContext executionContext) {
        logger.info(executionContext.getTrigger().getKey() + "=================rest任务执行完后");
        logger.info("\r\n");
    }
}

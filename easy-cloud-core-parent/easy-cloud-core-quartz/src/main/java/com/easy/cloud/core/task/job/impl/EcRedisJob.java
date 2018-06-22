package com.easy.cloud.core.task.job.impl;

import com.easy.cloud.core.task.executor.pojo.dto.EcTaskExecutorDTO;
import com.easy.cloud.core.task.job.EcBaseJob;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * @author daiqi
 * @create 2018-06-22 11:18
 */
@Component
public class EcRedisJob extends EcBaseJob {

    @Override
    protected String executeJob(JobExecutionContext executionContext, EcTaskExecutorDTO taskExecutorDTO) {
        logger.info("使用redis执行job");
        return null;
    }
}

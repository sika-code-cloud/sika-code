package com.easy.cloud.core.task.job.impl;

import com.easy.cloud.core.common.http.pojo.bo.EcHttpRequestTemplateBO;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.task.executor.pojo.dto.EcTaskExecutorDTO;
import com.easy.cloud.core.task.job.EcBaseJob;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * 使用rest的方式处理job
 *
 * @author daiqi
 * @create 2018-06-20 19:56
 */
@Component
public class EcRestJob extends EcBaseJob {
    @Override
    protected String executeJob(JobExecutionContext executionContext, EcTaskExecutorDTO taskExecutorDTO) {
        logger.info(executionContext.getTrigger().getKey() + "=================rest");
        EcHttpRequestTemplateBO httpRequestTemplateBO = new EcHttpRequestTemplateBO();
        String result = httpRequestTemplateBO.postForObject(taskExecutorDTO.getRequestUrl(), taskExecutorDTO.getRequestBody(), String.class);
        logger.info("返回的结果为：" + result);
        return result;
    }
}

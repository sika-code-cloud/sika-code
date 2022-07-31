package com.sika.code.batch.standard.listener;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.sika.code.batch.core.listener.BaseStepExecutionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 11:05
 */
@Slf4j
public class StandardStepExecutionListener implements BaseStepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("步骤id=【{}】开始执行，实例对象【{}】，JOB参数【{}】, 开始时间：{}",
                stepExecution.getId(), JSON.toJSONString(stepExecution.getJobExecution().getJobInstance()),
                JSON.toJSONString(stepExecution.getJobParameters()), DateUtil.formatDateTime(stepExecution.getStartTime()));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("步骤id=【{}】执行完成，实例参数【{}】，JOB参数【{}】, 执行状态【{}】，结束于：【{}】，所用时间为：{}ms",
                stepExecution.getId(), JSON.toJSONString(stepExecution.getJobExecution().getJobInstance()),
                JSON.toJSONString(stepExecution.getJobParameters()), stepExecution.getStatus(),
                DateUtil.formatDateTime(new Date()), (System.currentTimeMillis()- stepExecution.getStartTime().getTime()));
        return null;
    }
}

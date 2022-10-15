package com.sika.code.batch.standard.listener;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.sika.code.batch.core.listener.BaseStepExecutionListener;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;

import java.util.Date;
import java.util.Map;

import static com.sika.code.batch.standard.constant.BatchConstant.STEP_EXIT_STATUS;

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
@Setter
public class StandardStepExecutionListener implements BaseStepExecutionListener {
    private Map<String, Object> contextMap;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("步骤id=【{}】开始执行，实例对象【{}】, 开始时间：{}，JOB参数【{}】",
                stepExecution.getId(), JSON.toJSONString(stepExecution.getJobExecution().getJobInstance()),
                DateUtil.formatDateTime(stepExecution.getStartTime()), JSON.toJSONString(stepExecution.getJobParameters()));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("步骤id=【{}】执行完成， 读取的条数【{}】，写入的条数【{}】，执行状态【{}】，结束于：【{}】，所用时间为：{}ms，JOB参数【{}】,实例参数【{}】，步骤详情参数：【{}】",
                stepExecution.getId(), stepExecution.getReadCount(), stepExecution.getWriteCount(), stepExecution.getStatus(),
                DateUtil.formatDateTime(new Date()), (System.currentTimeMillis() - stepExecution.getStartTime().getTime()),
                JSON.toJSONString(stepExecution.getJobParameters()),
                JSON.toJSONString(stepExecution.getJobExecution().getJobInstance()),
                JSON.toJSONString(stepExecution));
        contextMap.put(STEP_EXIT_STATUS, stepExecution.getExitStatus());
        return stepExecution.getExitStatus();
    }
}

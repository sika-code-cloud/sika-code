package com.sika.code.batch.standard.listener;

import cn.hutool.core.date.DateUtil;
import com.sika.code.core.base.util.JSONUtil;
import com.sika.code.core.util.BeanUtil;
import com.sika.code.batch.core.listener.BaseJobExecutionListener;
import com.sika.code.batch.standard.store.StandardMapJobRepositoryFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;

import java.util.Date;
import java.util.Objects;

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
public class StandardJobExecutionListener implements BaseJobExecutionListener {
    static final String BLANK = "\n\r";

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info(BLANK);
        log.info("任务id=【{}】开始执行，实例参数【{}】，JOB参数【{}】, 开始时间：{}",
                jobExecution.getJobId(), JSONUtil.toJSONString(jobExecution.getJobInstance()),
                JSONUtil.toJSONString(jobExecution.getJobParameters()), DateUtil.formatDateTime(jobExecution.getStartTime()));
    }


    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("任务id=【{}】执行完成，JOB参数【{}】,  执行状态【{}】，结束于：【{}】，所用时间为：{}ms，实例参数【{}】",
                jobExecution.getJobId(),
                JSONUtil.toJSONString(jobExecution.getJobParameters()), jobExecution.getStatus(),
                DateUtil.formatDateTime(new Date()), (System.currentTimeMillis() - Objects.requireNonNull(jobExecution.getStartTime()).getTime()),
                JSONUtil.toJSONString(jobExecution.getJobInstance()));
        clearRepositoryData(jobExecution);
        log.info(BLANK);
    }

    // 为了防止内存数据被撑爆-每个job执行完成后-执行清理
    protected void clearRepositoryData(JobExecution jobExecution) {
        log.info("任务id=【{}】清除缓存数据，实例参数【{}】", jobExecution.getJobId(), JSONUtil.toJSONString(jobExecution.getJobInstance()));
        StandardMapJobRepositoryFactoryBean jobRepositoryFactoryBean = BeanUtil.getBean(StandardMapJobRepositoryFactoryBean.class);
        jobRepositoryFactoryBean.clear(jobExecution);

    }
}

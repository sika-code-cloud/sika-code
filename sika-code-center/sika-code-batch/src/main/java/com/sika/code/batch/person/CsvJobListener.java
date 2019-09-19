package com.sika.code.batch.person;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author daiqi
 * @create 2019-09-10 0:33
 */
@Slf4j
public class CsvJobListener implements JobExecutionListener {
    private static Long beginTime = 0L;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        beginTime = System.currentTimeMillis();
        log.info("beforeJob:jobExecution{}", jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("afterJob:jobExecution{}", jobExecution);
        log.info("-------------执行的时间为:{}-------------", (System.currentTimeMillis() - beginTime));
    }

}

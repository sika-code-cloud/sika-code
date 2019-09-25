package com.sika.code.batch.animal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author daiqi
 * @create 2019-09-10 0:33
 */
@Slf4j
public class CsvJobListener1 implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
      log.info("beforeJob11111111111111111:jobExecution{}", jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("afterJob111111111111111111:jobExecution{}", jobExecution);
    }
}

package com.sika.code.batch.listener;

import com.sika.code.common.log.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * @author daiqi
 * @create 2019-10-04 22:29
 */
@Slf4j
public class DefaultStepExecutionListener implements StepExecutionListener {
    private static Long beginTime = 0L;

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        LogUtil.info("afterStep", stepExecution.toString(), log);
        log.info("-------------执行的时间为:{}-------------", (System.currentTimeMillis() - beginTime));
        return stepExecution.getExitStatus();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        beginTime = System.currentTimeMillis();
        LogUtil.info("beforeStep", stepExecution.toString(), log);
    }
}

package com.easy.cloud.core.worker.job;

import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.common.thread.factory.EcThreadFactory;
import com.easy.cloud.core.worker.executor.pojo.dto.EcTaskExecutorDTO;
import com.easy.cloud.core.worker.job.callback.EcJobCallBackHandler;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基础job类
 *
 * @author daiqi
 * @create 2018-06-21 17:23
 */
public abstract class EcBaseJob {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static ExecutorService jobExecutorService;

    static {
        jobExecutorService = Executors.newCachedThreadPool(new EcThreadFactory("jobExecutorService"));
    }

    public void execute(JobExecutionContext executionContext, EcTaskExecutorDTO taskExecutorDTO) throws JobExecutionException {
        executeJobBefore(executionContext, taskExecutorDTO);
        if (taskExecutorDTO.isAsyn()) {
            asynExecuteJob(executionContext, taskExecutorDTO);
        } else {
            executeJob(executionContext, taskExecutorDTO);
        }
        executeJobAfter(executionContext, taskExecutorDTO);
    }


    /**
     * <p>
     * 执行job
     * </p>
     * <pre>
     *     子类通过重写该方法执行自己的业务逻辑
     * </pre>
     *
     * @param executionContext
     * @param taskExecutorDTO
     * @return void
     * @author daiqi
     * @date 2018/6/22 11:28
     */
    protected abstract String executeJob(JobExecutionContext executionContext, EcTaskExecutorDTO taskExecutorDTO);

    /**
     * <p>
     * 异步方式执行
     * </p>
     *
     * @param executionContext
     * @param taskExecutorDTO
     * @return void
     * @author daiqi
     * @date 2018/6/22 13:57
     */
    private final void asynExecuteJob(JobExecutionContext executionContext, EcTaskExecutorDTO taskExecutorDTO) {
        jobExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = executeJob(executionContext, taskExecutorDTO);
                    String jobCallbackClassName = taskExecutorDTO.getJobCallbackClassName();
                    if (EcStringUtils.isNotEmpty(jobCallbackClassName)) {
                        Class<?> callbackClass = Class.forName(jobCallbackClassName);
                        EcJobCallBackHandler jobCallBackHandler = (EcJobCallBackHandler) callbackClass.newInstance();
                        jobCallBackHandler.execute(result);
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * <p>
     * 执行job之前的相关操作 子类通过重写该方法实现对应的业务逻辑
     * </p>
     *
     * @param executionContext
     * @param taskExecutorDTO
     * @return java.lang.Object
     * @author daiqi
     * @date 2018/6/21 17:30
     */
    protected void executeJobBefore(JobExecutionContext executionContext, EcTaskExecutorDTO taskExecutorDTO) {

    }

    /**
     * <p>
     * 执行job之后的相关操作 子类通过重写该方法实现对应的业务逻辑
     * </p>
     *
     * @param executionContext
     * @param taskExecutorDTO
     * @return java.lang.Object
     * @author daiqi
     * @date 2018/6/21 17:30
     */
    protected void executeJobAfter(JobExecutionContext executionContext, EcTaskExecutorDTO taskExecutorDTO) {

    }
}

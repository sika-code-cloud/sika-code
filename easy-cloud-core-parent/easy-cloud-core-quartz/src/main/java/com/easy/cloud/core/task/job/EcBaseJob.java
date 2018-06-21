package com.easy.cloud.core.task.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 基础job类
 *
 * @author daiqi
 * @create 2018-06-21 17:23
 */
public abstract class EcBaseJob implements Job {

    @Override
    public void execute(JobExecutionContext executionContext) throws JobExecutionException {
        executeJobBefore(executionContext);
        executeJob(executionContext);
        executeJobAfter(executionContext);
    }


    /**
     * <p>
     * 执行job 子类通过重写该方法实现对应的业务逻辑
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     * </pre>
     *
     * @param executionContext
     * @return java.lang.Object
     * @author daiqi
     * @date 2018/6/21 17:30
     */
    protected abstract void executeJob(JobExecutionContext executionContext);

    /**
     * <p>
     * 执行job之前的相关操作 子类通过重写该方法实现对应的业务逻辑
     * </p>
     *
     * @param executionContext
     * @return java.lang.Object
     * @author daiqi
     * @date 2018/6/21 17:30
     */
    protected void executeJobBefore(JobExecutionContext executionContext) {

    }

    /**
     * <p>
     * 执行job之后的相关操作 子类通过重写该方法实现对应的业务逻辑
     * </p>
     *
     * @param executionContext
     * @return java.lang.Object
     * @author daiqi
     * @date 2018/6/21 17:30
     */
    protected void executeJobAfter(JobExecutionContext executionContext) {

    }
}

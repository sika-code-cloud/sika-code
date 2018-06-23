package com.easy.cloud.core.worker.executor.pojo.entity;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

/**
 * 描述：任务执行者表持久化类
 *
 * @author THINK
 * @date 2018-06-21 19:21:54
 */
public class EcTaskExecutorEntity extends EcBaseEntity {
    /**
     * 任务工作类型
     */
    private Integer jobType;
    /**
     * 任务工作分组
     */
    private String jobGroup;
    /**
     * 任务工作名称
     */
    private String jobName;
    /**
     * 任务触发器分组
     */
    private String triggerGroup;
    /**
     * 任务触发器名称
     */
    private String triggerName;
    /**
     * 重试次数
     */
    private Integer retryCount;
    /**
     * 重试间隔
     */
    private Integer retryInterval;
    /**
     * 请求的url
     */
    private String requestUrl;
    /**
     * 执行的结果
     */
    private String executeResult;
    /**
     * 任务执行者编号
     */
    private String taskExecutorNo;
    /**
     * 请求的方法统一使用post
     */
    private String requestMethod;
    /**
     * 请求的主体内容[json对象字符串]
     */
    private String requestBody;
    /**
     * 任务工作回调的完整类名
     */
    private String jobCallbackClassName;
    /**
     * 是否为异步执行 0:同步 1:异步
     */
    private Integer asyn;

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    /**
     * 获取任务工作分组
     */
    public String getJobGroup() {
        return this.jobGroup;
    }

    /**
     * 设置任务工作分组
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    /**
     * 构建任务工作分组
     */
    public EcTaskExecutorEntity buildJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
        return this;
    }

    /**
     * 获取任务工作名称
     */
    public String getJobName() {
        return this.jobName;
    }

    /**
     * 设置任务工作名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 构建任务工作名称
     */
    public EcTaskExecutorEntity buildJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    /**
     * 获取任务触发器分组
     */
    public String getTriggerGroup() {
        return this.triggerGroup;
    }

    /**
     * 设置任务触发器分组
     */
    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    /**
     * 构建任务触发器分组
     */
    public EcTaskExecutorEntity buildTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
        return this;
    }

    /**
     * 获取任务触发器名称
     */
    public String getTriggerName() {
        return this.triggerName;
    }

    /**
     * 设置任务触发器名称
     */
    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    /**
     * 构建任务触发器名称
     */
    public EcTaskExecutorEntity buildTriggerName(String triggerName) {
        this.triggerName = triggerName;
        return this;
    }

    /**
     * 获取重试次数
     */
    public Integer getRetryCount() {
        return this.retryCount;
    }

    /**
     * 设置重试次数
     */
    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    /**
     * 构建重试次数
     */
    public EcTaskExecutorEntity buildRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    /**
     * 获取重试间隔
     */
    public Integer getRetryInterval() {
        return this.retryInterval;
    }

    /**
     * 设置重试间隔
     */
    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    /**
     * 构建重试间隔
     */
    public EcTaskExecutorEntity buildRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
        return this;
    }

    /**
     * 获取请求的url
     */
    public String getRequestUrl() {
        return this.requestUrl;
    }

    /**
     * 设置请求的url
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * 构建请求的url
     */
    public EcTaskExecutorEntity buildRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
        return this;
    }

    /**
     * 获取执行的结果
     */
    public String getExecuteResult() {
        return this.executeResult;
    }

    /**
     * 设置执行的结果
     */
    public void setExecuteResult(String executeResult) {
        this.executeResult = executeResult;
    }

    /**
     * 构建执行的结果
     */
    public EcTaskExecutorEntity buildExecuteResult(String executeResult) {
        this.executeResult = executeResult;
        return this;
    }

    /**
     * 获取任务执行者编号
     */
    public String getTaskExecutorNo() {
        return this.taskExecutorNo;
    }

    /**
     * 设置任务执行者编号
     */
    public void setTaskExecutorNo(String taskExecutorNo) {
        this.taskExecutorNo = taskExecutorNo;
    }

    /**
     * 构建任务执行者编号
     */
    public EcTaskExecutorEntity buildTaskExecutorNo(String taskExecutorNo) {
        this.taskExecutorNo = taskExecutorNo;
        return this;
    }

    /**
     * 获取请求的方法统一使用post
     */
    public String getRequestMethod() {
        return this.requestMethod;
    }

    /**
     * 设置请求的方法统一使用post
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * 构建请求的方法统一使用post
     */
    public EcTaskExecutorEntity buildRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    /**
     * 获取请求的主体内容[json对象字符串]
     */
    public String getRequestBody() {
        return this.requestBody;
    }

    /**
     * 设置请求的主体内容[json对象字符串]
     */
    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    /**
     * 构建请求的主体内容[json对象字符串]
     */
    public EcTaskExecutorEntity buildRequestBody(String requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public String getJobCallbackClassName() {
        return jobCallbackClassName;
    }

    public void setJobCallbackClassName(String jobCallbackClassName) {
        this.jobCallbackClassName = jobCallbackClassName;
    }

    public Integer getAsyn() {
        return asyn;
    }

    public void setAsyn(Integer asyn) {
        this.asyn = asyn;
    }
}

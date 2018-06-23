package com.easy.cloud.core.worker.executor.pojo.query;

import com.easy.cloud.core.basic.pojo.query.EcBaseQuery;

/**
 * 描述：查询类
 * 
 * @author THINK
 * @date 2018-06-21 19:21:54
 */
public class EcTaskExecutorQuery extends EcBaseQuery {
    /** 任务工作类型 */
    private String jobType;
    /** 任务工作分组 */
    private String jobGroup;
    /** 任务工作名称 */
    private String jobName;
    /** 任务触发器分组 */
    private String triggerGroup;
    /** 任务触发器名称 */
    private String triggerName;
    /** 任务请求url */
    private String requestUrl;
    /** 任务执行者编号 */
    private String taskExecutorNo;


    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTaskExecutorNo() {
        return taskExecutorNo;
    }

    public void setTaskExecutorNo(String taskExecutorNo) {
        this.taskExecutorNo = taskExecutorNo;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}

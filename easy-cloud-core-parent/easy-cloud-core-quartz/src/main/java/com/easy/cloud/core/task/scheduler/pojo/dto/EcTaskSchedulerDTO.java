package com.easy.cloud.core.task.scheduler.pojo.dto;

import com.easy.cloud.core.task.executor.pojo.dto.EcTaskExecutorDTO;

import java.io.Serializable;
import java.util.List;

/**
 * 任务调度数据传输对象
 *
 * @author daiqi
 * @create 2018-06-20 11:30
 */
public class EcTaskSchedulerDTO implements Serializable {

    private EcTaskExecutorDTO taskExecutor;

    public EcTaskSchedulerDTO() {
        if (this.taskExecutor == null) {
            this.taskExecutor = new EcTaskExecutorDTO();
        }
    }


    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器分组
     */
    private String triggerGroup;

    /**
     * 任务状态 0禁用 1启用 2删除
     */
    private Integer jobStatus;

    /**
     * 任务运行时间表达式
     */
    private String cronExpression;
    /**
     * 假期列表
     */
    private List<EcHolidayDTO> holidayDTOs;
    /**
     * 假期名称
     */
    private String holidayName;
    /**
     * 描述
     */
    private String description;


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }


    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public List<EcHolidayDTO> getHolidayDTOs() {
        return holidayDTOs;
    }

    public void setHolidayDTOs(List<EcHolidayDTO> holidayDTOs) {
        this.holidayDTOs = holidayDTOs;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EcTaskExecutorDTO getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(EcTaskExecutorDTO taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public EcTaskSchedulerDTO jobName(String jobName) {
        setJobName(jobName);
        return this;
    }

    public EcTaskSchedulerDTO jobGroup(String jobGroup) {
        setJobGroup(jobGroup);
        return this;
    }

    public EcTaskSchedulerDTO triggerName(String triggerName) {
        setTriggerName(triggerName);
        return this;
    }

    public EcTaskSchedulerDTO triggerGroup(String triggerGroup) {
        setTriggerGroup(triggerGroup);
        return this;
    }

    public EcTaskSchedulerDTO jobStatus(Integer jobStatus) {
        setJobStatus(jobStatus);
        return this;
    }

    public EcTaskSchedulerDTO cronExpression(String cronExpression) {
        setCronExpression(cronExpression);
        return this;
    }

    public EcTaskSchedulerDTO holidayDTOs(List<EcHolidayDTO> holidayDTOs) {
        setHolidayDTOs(holidayDTOs);
        return this;
    }

    public EcTaskSchedulerDTO holidayName(String holidayName) {
        setHolidayName(holidayName);
        return this;
    }

    public EcTaskSchedulerDTO description(String description) {
        setDescription(description);
        return this;
    }

    public void buildTaskExecutorData() {
        this.taskExecutor.setJobName(this.jobName);
        this.taskExecutor.setJobGroup(this.jobGroup);
        this.taskExecutor.setTriggerGroup(this.triggerGroup);
        this.taskExecutor.setTriggerName(this.triggerName);
    }

    public String getTriggerKey() {
        return triggerGroup + "." + triggerName;
    }

    public String getJobKey() {
        return jobGroup + "." + jobName;
    }

}

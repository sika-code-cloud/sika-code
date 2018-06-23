package com.easy.cloud.core.worker.scheduler.pojo.bo;

import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.worker.scheduler.pojo.dto.EcTaskSchedulerDTO;

/**
 * 任务调度业务逻辑对象
 *
 * @author daiqi
 * @create 2018-06-20 11:30
 */
public class EcTaskSchedulerBO {

    private EcTaskSchedulerDTO taskSchedulerDTO;

    public EcTaskSchedulerBO(EcTaskSchedulerDTO taskSchedulerDTO) {
        this.taskSchedulerDTO = taskSchedulerDTO;
    }

    public void verifyInitTaskData() {
        verifyCommonData();
    }

    public void verifyUpdateTaskSchedulerData() {
        verifyCommonData();
    }

    public void verifyPauseTaskTriggerData() {
        verifyTriggerData();
    }

    public void verifyResumeTaskTriggerData() {
        verifyTriggerData();
    }

    public void verifyDeleteTriggerTriggerData() {
        verifyTriggerData();
    }

    private void verifyTriggerData() {
        EcAssert.verifyStrEmpty(taskSchedulerDTO.getTriggerName(), "triggerName");
        EcAssert.verifyStrEmpty(taskSchedulerDTO.getTriggerGroup(), "triggerGroup");
    }
    private void verifyCommonData() {
        EcAssert.verifyStrEmpty(taskSchedulerDTO.getCronExpression(), "cronExpression");
        EcAssert.verifyStrEmpty(taskSchedulerDTO.getJobGroup(), "jobGroup");
        EcAssert.verifyStrEmpty(taskSchedulerDTO.getJobName(), "jobName");
        verifyTriggerData();
    }

    public EcTaskSchedulerDTO getTaskSchedulerDTO() {
        return taskSchedulerDTO;
    }
}

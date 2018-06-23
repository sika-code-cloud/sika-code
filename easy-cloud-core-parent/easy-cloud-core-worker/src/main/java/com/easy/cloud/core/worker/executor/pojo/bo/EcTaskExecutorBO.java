package com.easy.cloud.core.worker.executor.pojo.bo;

import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.worker.executor.pojo.dto.EcTaskExecutorDTO;

/**
 * @author daiqi
 * @create 2018-06-21 19:57
 */
public class EcTaskExecutorBO {
    private EcTaskExecutorDTO taskExecutorDTO;

    public EcTaskExecutorBO(EcTaskExecutorDTO taskExecutorDTO) {
        this.taskExecutorDTO = taskExecutorDTO;
    }

    public void initSaveTaskExecutorData() {
        if (EcStringUtils.isEmpty(taskExecutorDTO.getTaskExecutorNo())) {
            taskExecutorDTO.setTaskExecutorNo(EcStringUtils.generateUUID());
        }
    }

    public void verifySaveTaskExecutorData() {
        EcAssert.verifyStrEmpty(taskExecutorDTO.getJobGroup(), "jobGroup");
        EcAssert.verifyStrEmpty(taskExecutorDTO.getJobName(), "jobName");
        EcAssert.verifyStrEmpty(taskExecutorDTO.getTriggerGroup(), "triggerGroup");
        EcAssert.verifyStrEmpty(taskExecutorDTO.getTriggerName(), "triggerName");
        EcAssert.verifyStrEmpty(taskExecutorDTO.getRequestUrl(), "requestUrl");
    }
}

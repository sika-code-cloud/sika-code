package com.easy.cloud.core.task.scheduler.service.impl;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.task.executor.pojo.dto.EcTaskExecutorDTO;
import com.easy.cloud.core.task.executor.service.EcTaskExecutorService;
import com.easy.cloud.core.task.job.EcRestJob;
import com.easy.cloud.core.task.scheduler.manager.EcTaskSchedulerManager;
import com.easy.cloud.core.task.scheduler.pojo.bo.EcTaskSchedulerBO;
import com.easy.cloud.core.task.scheduler.pojo.dto.EcTaskSchedulerDTO;
import com.easy.cloud.core.task.scheduler.service.EcTaskSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author daiqi
 * @create 2018-06-20 20:02
 */
@Service
public class EcTaskSchedulerServiceImpl implements EcTaskSchedulerService {
    @Autowired
    private EcTaskSchedulerManager taskSchedulerManager;
    @Autowired
    private EcTaskExecutorService taskExecutorService;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public EcBaseServiceResult initTask(EcTaskSchedulerDTO taskSchedulerDTO) {
        EcTaskSchedulerBO taskSchedulerBO = new EcTaskSchedulerBO(taskSchedulerDTO);
        taskSchedulerBO.verifyInitTaskData();
        taskSchedulerManager.initTask(taskSchedulerBO.getTaskSchedulerDTO(), EcRestJob.class);
        // 保存任务执行者信息
        taskExecutorService.saveTaskExecutor(taskSchedulerDTO.getTaskExecutor());

        return EcBaseServiceResult.newInstanceOfSuccess();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public EcBaseServiceResult updateTaskScheduler(EcTaskSchedulerDTO taskSchedulerDTO) {
        EcTaskSchedulerBO taskSchedulerBO = new EcTaskSchedulerBO(taskSchedulerDTO);
        taskSchedulerBO.verifyUpdateTaskSchedulerData();
        taskSchedulerManager.updateTaskScheduler(taskSchedulerBO.getTaskSchedulerDTO());
        return EcBaseServiceResult.newInstanceOfSuccess();
    }

    @Override
    public EcBaseServiceResult pauseTaskTrigger(EcTaskSchedulerDTO taskSchedulerDTO) {
        EcTaskSchedulerBO taskSchedulerBO = new EcTaskSchedulerBO(taskSchedulerDTO);
        taskSchedulerBO.verifyPauseTaskTriggerData();
        taskSchedulerManager.pauseTaskTrigger(taskSchedulerBO.getTaskSchedulerDTO());
        return EcBaseServiceResult.newInstanceOfSuccess();
    }

    @Override
    public EcBaseServiceResult resumeTaskTrigger(EcTaskSchedulerDTO taskSchedulerDTO) {
        EcTaskSchedulerBO taskSchedulerBO = new EcTaskSchedulerBO(taskSchedulerDTO);
        taskSchedulerBO.verifyResumeTaskTriggerData();
        taskSchedulerManager.resumeTaskTrigger(taskSchedulerBO.getTaskSchedulerDTO());
        return EcBaseServiceResult.newInstanceOfSuccess();
    }

    @Override
    public EcBaseServiceResult deleteTrigger(EcTaskSchedulerDTO taskSchedulerDTO) {
        EcTaskSchedulerBO taskSchedulerBO = new EcTaskSchedulerBO(taskSchedulerDTO);
        taskSchedulerBO.verifyDeleteTriggerTriggerData();
        taskSchedulerManager.deleteTrigger(taskSchedulerBO.getTaskSchedulerDTO());
        return EcBaseServiceResult.newInstanceOfSuccess();
    }
}

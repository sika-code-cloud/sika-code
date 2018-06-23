package com.easy.cloud.core.worker.executor.service.impl;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.worker.executor.dao.EcTaskExecutorDAO;
import com.easy.cloud.core.worker.executor.pojo.bo.EcTaskExecutorBO;
import com.easy.cloud.core.worker.executor.pojo.dto.EcTaskExecutorDTO;
import com.easy.cloud.core.worker.executor.pojo.entity.EcTaskExecutorEntity;
import com.easy.cloud.core.worker.executor.pojo.query.EcTaskExecutorQuery;
import com.easy.cloud.core.worker.executor.service.EcTaskExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：服务实现类
 *
 * @author THINK
 * @date 2018-06-21 19:21:54
 */
@Service(value = "taskExecutorService")
public class EcTaskExecutorServiceImpl extends EcBaseService implements EcTaskExecutorService {
    /**
     * 数据处理接口
     */
    @Autowired
    private EcTaskExecutorDAO taskExecutorDAO;

    @Override
    public List<EcTaskExecutorDTO> listByTaskData(EcTaskExecutorQuery taskExecutorQuery) {
        verifyListByTaskData(taskExecutorQuery);
        List<EcTaskExecutorEntity> taskExecutorEntities = taskExecutorDAO.listByQuery(taskExecutorQuery);
        EcAssert.verifyListEmpty(taskExecutorEntities, "taskExecutorEntities");
        return EcJSONUtils.parseArray(taskExecutorEntities, EcTaskExecutorDTO.class);
    }

    @Override
    public EcTaskExecutorDTO saveTaskExecutor(EcTaskExecutorDTO taskExecutorDTO) {
        EcTaskExecutorBO taskExecutorBO = new EcTaskExecutorBO(taskExecutorDTO);
        taskExecutorBO.initSaveTaskExecutorData();
        taskExecutorBO.verifySaveTaskExecutorData();
        EcTaskExecutorEntity taskExecutorEntity = queryByTaskData(taskExecutorDTO);
        // 数据存在抛出异常
        EcAssert.verifyDataExistent(taskExecutorEntity, "taskExecutorEntity");
        taskExecutorDAO.save(EcJSONUtils.parseObject(taskExecutorDTO, EcTaskExecutorEntity.class));
        return taskExecutorDTO;
    }

    @Override
    public EcBaseServiceResult updateTaskExecutor(EcTaskExecutorDTO taskExecutorDTO) {
        EcAssert.verifyObjNull(taskExecutorDTO, "taskExecutorDTO");
        EcTaskExecutorEntity taskExecutorEntity = queryByTaskData(taskExecutorDTO);
        // 数据不存在抛出异常
        EcAssert.verifyDataNotExistent(taskExecutorEntity, "taskExecutorEntity");
        taskExecutorEntity = EcBaseUtils.copeFromObjToTargetObj(taskExecutorDTO, taskExecutorEntity);
        // 更新
        taskExecutorDAO.update(taskExecutorEntity);
        return EcBaseServiceResult.newInstanceOfSuccess();
    }

    /**
     * <p>
     * 根据任务数据获取任务执行者实体信息
     * </p>
     *
     * @param taskExecutorDTO
     * @return com.easy.cloud.core.worker.executor.pojo.entity.EcTaskExecutorEntity
     * @author daiqi
     * @date 2018/6/22 15:45
     */
    private EcTaskExecutorEntity queryByTaskData(EcTaskExecutorDTO taskExecutorDTO) {
        EcTaskExecutorQuery taskExecutorQuery = convertDTOToQuery(taskExecutorDTO);
        List<EcTaskExecutorEntity> taskExecutorEntities = taskExecutorDAO.listByQuery(taskExecutorQuery);
        if (EcCollectionsUtils.isEmpty(taskExecutorEntities)) {
            return null;
        }
        return taskExecutorEntities.get(0);
    }

    private EcTaskExecutorQuery convertDTOToQuery(EcTaskExecutorDTO taskExecutorDTO) {
        EcTaskExecutorQuery taskExecutorQuery = new EcTaskExecutorQuery();
        taskExecutorQuery.setJobGroup(taskExecutorDTO.getJobGroup());
        taskExecutorQuery.setJobName(taskExecutorDTO.getJobName());
        taskExecutorQuery.setTriggerGroup(taskExecutorDTO.getTriggerGroup());
        taskExecutorQuery.setTriggerName(taskExecutorDTO.getTriggerName());
        taskExecutorQuery.setRequestUrl(taskExecutorDTO.getRequestUrl());
        taskExecutorQuery.setTaskExecutorNo(taskExecutorDTO.getTaskExecutorNo());
        return taskExecutorQuery;
    }

    private void verifyListByTaskData(EcTaskExecutorQuery taskExecutorQuery) {
        EcAssert.verifyObjNull(taskExecutorQuery, "taskExecutorQuery");
        EcAssert.verifyObjNull(taskExecutorQuery.getJobGroup(), "jobGroup");
        EcAssert.verifyObjNull(taskExecutorQuery.getJobName(), "jobName");
        EcAssert.verifyObjNull(taskExecutorQuery.getTriggerGroup(), "triggerGroup");
        EcAssert.verifyObjNull(taskExecutorQuery.getTriggerName(), "triggerName");
    }
}

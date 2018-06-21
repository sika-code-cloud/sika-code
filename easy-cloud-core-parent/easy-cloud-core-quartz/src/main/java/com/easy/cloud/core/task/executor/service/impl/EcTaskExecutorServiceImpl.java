package com.easy.cloud.core.task.executor.service.impl;

import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.task.executor.dao.EcTaskExecutorDAO;
import com.easy.cloud.core.task.executor.pojo.bo.EcTaskExecutorBO;
import com.easy.cloud.core.task.executor.pojo.dto.EcTaskExecutorDTO;
import com.easy.cloud.core.task.executor.pojo.entity.EcTaskExecutorEntity;
import com.easy.cloud.core.task.executor.pojo.query.EcTaskExecutorQuery;
import com.easy.cloud.core.task.executor.service.EcTaskExecutorService;
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
	/** null数据处理接口 */
	@Autowired
	private EcTaskExecutorDAO taskExecutorDAO;

	@Override
	public List<EcTaskExecutorDTO> listByTaskData(EcTaskExecutorQuery taskExecutorQuery) {
		verifyListByTaskData(taskExecutorQuery);
		List<EcTaskExecutorEntity> taskExecutorEntities = taskExecutorDAO.listByQuery(taskExecutorQuery);
		return EcJSONUtils.parseArray(taskExecutorEntities, EcTaskExecutorDTO.class);
	}

	@Override
	public EcTaskExecutorDTO saveTaskExecutor(EcTaskExecutorDTO taskExecutorDTO) {
		EcTaskExecutorBO taskExecutorBO = new EcTaskExecutorBO(taskExecutorDTO);
		taskExecutorBO.initSaveTaskExecutorData();
		taskExecutorBO.verifySaveTaskExecutorData();
		taskExecutorDAO.save(EcJSONUtils.parseObject(taskExecutorDTO, EcTaskExecutorEntity.class));
		return taskExecutorDTO;
	}

	private void verifyListByTaskData(EcTaskExecutorQuery taskExecutorQuery) {
		EcAssert.verifyObjNull(taskExecutorQuery, "taskExecutorQuery");
		EcAssert.verifyObjNull(taskExecutorQuery.getJobGroup(), "jobGroup");
		EcAssert.verifyObjNull(taskExecutorQuery.getJobName(), "jobName");
		EcAssert.verifyObjNull(taskExecutorQuery.getTriggerGroup(), "triggerGroup");
		EcAssert.verifyObjNull(taskExecutorQuery.getTriggerName(), "triggerName");
	}
}

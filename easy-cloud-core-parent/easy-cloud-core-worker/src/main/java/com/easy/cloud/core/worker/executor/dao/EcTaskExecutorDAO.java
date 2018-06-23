package com.easy.cloud.core.worker.executor.dao;

import com.easy.cloud.core.worker.executor.pojo.entity.EcTaskExecutorEntity;
import com.easy.cloud.core.jdbc.base.dao.EcBaseDAO;
import com.easy.cloud.core.worker.executor.pojo.query.EcTaskExecutorQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：数据处理接口
 * 
 * @author THINK
 * @date 2018-06-21 19:21:54
 */
public interface EcTaskExecutorDAO extends EcBaseDAO<EcTaskExecutorEntity> {
    List<EcTaskExecutorEntity> listByQuery(@Param(value = "query") EcTaskExecutorQuery executorQuery);
}

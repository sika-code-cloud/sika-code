package com.easy.cloud.core.reptile.engine.dao;


import org.apache.ibatis.annotations.Param;

import com.easy.cloud.core.jdbc.base.dao.EcBaseDAO;
import com.easy.cloud.core.reptile.engine.pojo.entity.EcReptileEngineEntity;
import com.easy.cloud.core.reptile.engine.pojo.query.EcReptileEngineQuery;

/**
 * 描述：数据处理接口
 * 
 * @author THINK
 * @date 2018-06-11 10:59:59
 */
public interface EcReptileEngineDAO extends EcBaseDAO<EcReptileEngineEntity> {
	EcReptileEngineEntity queryGeccoEngineByNo(@Param(value = "query") EcReptileEngineQuery query);
}

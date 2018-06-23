package com.easy.cloud.core.reptile.datafield.dao;

import com.easy.cloud.core.reptile.datafield.pojo.entity.EcReptileDataFieldEntity;
import com.easy.cloud.core.reptile.datafield.pojo.query.EcReptileDataFieldQuery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easy.cloud.core.jdbc.base.dao.EcBaseDAO;

/**
 * 描述：数据处理接口
 * 
 * @author THINK
 * @date 2018-06-07 18:09:33
 */
public interface EcReptileDataFieldDAO extends EcBaseDAO<EcReptileDataFieldEntity> {
	List<EcReptileDataFieldEntity> listByQuery(@Param(value = "query") EcReptileDataFieldQuery reptileDataFieldQuery);
}

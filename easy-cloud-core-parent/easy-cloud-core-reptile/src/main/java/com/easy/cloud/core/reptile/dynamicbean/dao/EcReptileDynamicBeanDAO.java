package com.easy.cloud.core.reptile.dynamicbean.dao;

import com.easy.cloud.core.reptile.dynamicbean.pojo.entity.EcReptileDynamicBeanEntity;
import com.easy.cloud.core.reptile.dynamicbean.pojo.query.EcReptileDynamicBeanQuery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easy.cloud.core.jdbc.base.dao.EcBaseDAO;

/**
 * 描述：数据处理接口
 * 
 * @author daiqi
 * @date 2018-06-07 18:09:16
 */
public interface EcReptileDynamicBeanDAO extends EcBaseDAO<EcReptileDynamicBeanEntity> {
	List<EcReptileDynamicBeanEntity> listByQuery(@Param(value = "query") EcReptileDynamicBeanQuery dynamicBeanQuery);
}

package com.easy.cloud.core.operator.sysfilterconfig.dao;

import com.easy.cloud.core.operator.sysfilterconfig.pojo.entity.SysFilterConfigEntity;
import com.easy.cloud.core.jdbc.base.dao.EcBaseDAO;

import java.util.List;

/**
 * 描述：数据处理接口
 * 
 * @author daiqi
 * @date 2018-06-25 16:36:55
 */
public interface SysFilterConfigDAO extends EcBaseDAO<SysFilterConfigEntity> {
		List<SysFilterConfigEntity> listAll();
}

package com.easy.cloud.core.operator.sysroleresource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.operator.sysroleresource.dao.SysRoleResourceDAO;
import com.easy.cloud.core.operator.sysroleresource.pojo.dto.SysRoleResourceDTO;
import com.easy.cloud.core.operator.sysroleresource.pojo.entity.SysRoleResourceEntity;
import com.easy.cloud.core.operator.sysroleresource.service.SysRoleResourceService;

/**
 * 描述：服务实现类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:33
 */
@Service(value = "sysRoleResourceService")
public class SysRoleResourceServiceImpl extends EcBaseService implements SysRoleResourceService {
	/** null数据处理接口 */
	@Autowired
	private SysRoleResourceDAO sysRoleResourceDAO;
	
	public EcBaseServiceResult save(SysRoleResourceDTO roleDTO) {
		SysRoleResourceEntity entity = EcJSONUtils.parseObject(roleDTO, SysRoleResourceEntity.class);
		sysRoleResourceDAO.save(entity);
		return EcBaseServiceResult.newInstanceOfSucResult(entity);
	}
		
}

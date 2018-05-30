package com.easy.cloud.core.operator.sysroleresource.service.impl;

import org.springframework.stereotype.Service;
import com.easy.cloud.core.basic.service.EcBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.easy.cloud.core.operator.sysroleresource.dao.SysRoleResourceDAO;
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
		
}

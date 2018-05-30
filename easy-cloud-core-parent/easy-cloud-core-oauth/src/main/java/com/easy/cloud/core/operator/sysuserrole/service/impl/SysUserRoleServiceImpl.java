package com.easy.cloud.core.operator.sysuserrole.service.impl;

import org.springframework.stereotype.Service;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.operator.sysuserrole.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import com.easy.cloud.core.operator.sysuserrole.dao.SysUserRoleDAO;

/**
 * 描述：服务实现类
 * 
 * @author THINK
 * @date 2018-05-30 16:23:59
 */
@Service(value = "sysUserRoleService")
public class SysUserRoleServiceImpl extends EcBaseService implements SysUserRoleService {
	/** null数据处理接口 */
	@Autowired
	private SysUserRoleDAO sysUserRoleDAO;
		
		
}

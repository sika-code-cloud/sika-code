package com.easy.cloud.core.operator.sysorganization.service.impl;

import com.easy.cloud.core.operator.sysorganization.service.SysOrganizationService;
import org.springframework.stereotype.Service;
import com.easy.cloud.core.basic.service.EcBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.easy.cloud.core.operator.sysorganization.dao.SysOrganizationDAO;

/**
 * 描述：服务实现类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:08
 */
@Service(value = "sysOrganizationService")
public class SysOrganizationServiceImpl extends EcBaseService implements SysOrganizationService {
	/** null数据处理接口 */
	@Autowired
	private SysOrganizationDAO sysOrganizationDAO;
		
		
}

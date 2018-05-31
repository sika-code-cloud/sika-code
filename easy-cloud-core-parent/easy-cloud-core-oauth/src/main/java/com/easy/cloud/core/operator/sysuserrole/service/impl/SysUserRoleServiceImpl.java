package com.easy.cloud.core.operator.sysuserrole.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuserrole.dao.SysUserRoleDAO;
import com.easy.cloud.core.operator.sysuserrole.pojo.dto.SysUserRoleDTO;
import com.easy.cloud.core.operator.sysuserrole.pojo.entity.SysUserRoleEntity;
import com.easy.cloud.core.operator.sysuserrole.service.SysUserRoleService;

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
		
	public EcBaseServiceResult save(SysUserRoleDTO sysUserRoleDTO) {
		Subject subject = SecurityUtils.getSubject();
		SysUserDTO userDTO = (SysUserDTO) subject.getSession().getAttribute("user");
		sysUserRoleDTO.setUserId(userDTO.getId());
		SysUserRoleEntity sysUserRoleEntity = EcJSONUtils.parseObject(sysUserRoleDTO, SysUserRoleEntity.class);
		sysUserRoleDAO.save(sysUserRoleEntity);
		return EcBaseServiceResult.newInstanceOfSucResult(sysUserRoleEntity);
	}
}

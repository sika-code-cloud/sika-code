package com.easy.cloud.core.operator.sysrole.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.operator.sysrole.dao.SysRoleDAO;
import com.easy.cloud.core.operator.sysrole.pojo.dto.SysRoleDTO;
import com.easy.cloud.core.operator.sysrole.pojo.entity.SysRoleEntity;
import com.easy.cloud.core.operator.sysrole.pojo.query.SysRoleQuery;
import com.easy.cloud.core.operator.sysrole.service.SysRoleService;

/**
 * 描述：服务实现类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:25
 */
@Service(value = "sysRoleService")
public class SysRoleServiceImpl extends EcBaseService implements SysRoleService {
	/** 数据处理接口 */
	@Autowired
	private SysRoleDAO sysRoleDAO;

	public EcBaseServiceResult save(SysRoleDTO roleDTO) {
		SysRoleEntity roleEntity = EcJSONUtils.parseObject(roleDTO, SysRoleEntity.class);
		sysRoleDAO.save(roleEntity);
		return EcBaseServiceResult.newInstanceOfSucResult(roleEntity);
	}

	public List<SysRoleEntity> findByUserId(Long userId) {
		SysRoleQuery query = new SysRoleQuery();
		query.setUserId(userId);
		return sysRoleDAO.listByQuery(query);
	}

}

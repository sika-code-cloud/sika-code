package com.easy.cloud.core.operator.sysrole.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
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

	@Override
	public EcBaseServiceResult save(SysRoleDTO roleDTO) {
		SysRoleEntity roleEntity = EcJSONUtils.parseObject(roleDTO, SysRoleEntity.class);
		sysRoleDAO.save(roleEntity);
		return EcBaseServiceResult.newInstanceOfSucResult(roleEntity);
	}

	@Override
	public List<SysRoleDTO> findByUserId(Long userId) {
		if (EcBaseUtils.isNull(userId)) {
			throw new EcBaseBusinessException("A_11111111", "用户编号不能为空");
		}
		SysRoleQuery query = new SysRoleQuery();
		query.setUserId(userId);

		List<SysRoleEntity> sysRoleEntities = sysRoleDAO.listByQuery(query);
		if (EcCollectionsUtils.isEmpty(sysRoleEntities)) {
			return new ArrayList<>();
		}
		return EcJSONUtils.parseArray(sysRoleEntities, SysRoleDTO.class);
	}

}

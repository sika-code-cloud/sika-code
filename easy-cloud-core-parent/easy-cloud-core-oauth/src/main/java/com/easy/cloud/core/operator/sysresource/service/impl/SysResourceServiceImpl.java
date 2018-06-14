package com.easy.cloud.core.operator.sysresource.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.operator.sysresource.dao.SysResourceDAO;
import com.easy.cloud.core.operator.sysresource.pojo.dto.SysResourceDTO;
import com.easy.cloud.core.operator.sysresource.pojo.entity.SysResourceEntity;
import com.easy.cloud.core.operator.sysresource.pojo.query.SysResourceQuery;
import com.easy.cloud.core.operator.sysresource.service.SysResourceService;

/**
 * 描述：服务实现类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:17
 */
@Service(value = "sysResourceService")
@EcLogAnnotation(logSwitch = false, analysisSwitch = false)
public class SysResourceServiceImpl extends EcBaseService implements SysResourceService {
	/** null数据处理接口 */
	@Autowired
	private SysResourceDAO sysResourceDAO;
	
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
	public EcBaseServiceResult save(SysResourceDTO resourceDTO) {
		SysResourceEntity resourceEntity = EcJSONUtils.parseObject(resourceDTO, SysResourceEntity.class);
		sysResourceDAO.save(resourceEntity);
		return EcBaseServiceResult.newInstanceOfSucResult(resourceEntity);
	}
	
	public List<SysResourceDTO> findByRoleNos(List<Integer> roleNos) {
		if (EcCollectionsUtils.isEmpty(roleNos)) {
			return null;
		}
		SysResourceQuery query = new SysResourceQuery();
		query.setRoleNos(roleNos);
		return EcJSONUtils.parseArray(sysResourceDAO.listByQuery(query), SysResourceDTO.class);
	}
	
	private List<SysResourceEntity> getInitData() {
		List<SysResourceEntity> sysResourceEntities = new ArrayList<>();
//		Integer resourceNo, String name, String type, String url, Integer parentNo,String parentNos, String permission
		sysResourceEntities.add(new SysResourceEntity(1, "资源", "menu", "", 0, "[0]", ""));
		
		sysResourceEntities.add(new SysResourceEntity(11, "组织机构管理", "menu", "/organization", 1, "[0,1]", "organization:*"));
		sysResourceEntities.add(new SysResourceEntity(12, "组织机构新增", "button", "", 11, "[0,1,11]", "organization:create"));
		sysResourceEntities.add(new SysResourceEntity(13, "组织机构修改", "button", "", 11, "[0,1,11]", "organization:update"));
		sysResourceEntities.add(new SysResourceEntity(14, "组织机构删除", "button", "", 11, "[0,1,11]", "organization:delete"));
		sysResourceEntities.add(new SysResourceEntity(15, "组织机构查看", "button", "", 11, "[0,1,11]", "organization:view"));
		
		sysResourceEntities.add(new SysResourceEntity(21, "用户管理", "menu", "/user", 1, "[0,1]", "user:*"));
		sysResourceEntities.add(new SysResourceEntity(22, "用户新增", "button", "", 21, "[0,1,21]", "user:create"));
		sysResourceEntities.add(new SysResourceEntity(23, "用户修改", "button", "", 21, "[0,1,21]", "user:update"));
		sysResourceEntities.add(new SysResourceEntity(24, "用户删除", "button", "", 21, "[0,1,21]", "user:delete"));
		sysResourceEntities.add(new SysResourceEntity(25, "用户查看", "button", "", 21, "[0,1,21]", "user:view"));
		
		sysResourceEntities.add(new SysResourceEntity(31, "资源管理", "menu", "/resource", 1, "[0,1]", "resource:*"));
		sysResourceEntities.add(new SysResourceEntity(32, "资源新增", "button", "", 31, "[0,1,31]", "resource:create"));
		sysResourceEntities.add(new SysResourceEntity(33, "资源修改", "button", "", 31, "[0,1,31]", "resource:update"));
		sysResourceEntities.add(new SysResourceEntity(34, "资源删除", "button", "", 31, "[0,1,31]", "resource:delete"));
		sysResourceEntities.add(new SysResourceEntity(35, "资源查看", "button", "", 31, "[0,1,31]", "resource:view"));
		
		sysResourceEntities.add(new SysResourceEntity(41, "角色管理", "menu", "/role", 1, "[0,1]", "role:*"));
		sysResourceEntities.add(new SysResourceEntity(42, "角色新增", "button", "", 41, "[0,1,41]", "role:create"));
		sysResourceEntities.add(new SysResourceEntity(43, "角色修改", "button", "", 41, "[0,1,41]", "role:update"));
		sysResourceEntities.add(new SysResourceEntity(44, "角色删除", "button", "", 41, "[0,1,41]", "role:delete"));
		sysResourceEntities.add(new SysResourceEntity(45, "角色查看", "button", "", 41, "[0,1,41]", "role:view"));
		
		return sysResourceEntities;
	}
		
}

package com.easy.cloud.core.operator.sysresource.service;

import java.util.List;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysresource.pojo.dto.SysResourceDTO;

/**
 * 描述：服务接口
 * 
 * @author THINK
 * @date 2018-05-30 16:24:17
 */
public interface SysResourceService {
	EcBaseServiceResult save(SysResourceDTO resourceDTO);
	
	List<SysResourceDTO> findByRoleNos(List<Integer> roleNos);
}

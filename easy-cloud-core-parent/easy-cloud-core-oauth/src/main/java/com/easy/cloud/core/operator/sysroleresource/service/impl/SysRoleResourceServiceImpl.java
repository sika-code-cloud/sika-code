package com.easy.cloud.core.operator.sysroleresource.service.impl;

import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.operator.sysuserrole.pojo.entity.SysUserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.operator.sysroleresource.dao.SysRoleResourceDAO;
import com.easy.cloud.core.operator.sysroleresource.pojo.dto.SysRoleResourceDTO;
import com.easy.cloud.core.operator.sysroleresource.pojo.entity.SysRoleResourceEntity;
import com.easy.cloud.core.operator.sysroleresource.service.SysRoleResourceService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：服务实现类
 *
 * @author THINK
 * @date 2018-05-30 16:24:33
 */
@Service(value = "sysRoleResourceService")
public class SysRoleResourceServiceImpl extends EcBaseService implements SysRoleResourceService {
    /**
     * null数据处理接口
     */
    @Autowired
    private SysRoleResourceDAO sysRoleResourceDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EcBaseServiceResult saveSysRoleResource(SysRoleResourceDTO roleDTO) {
        SysRoleResourceEntity entity = EcJSONUtils.parseObject(roleDTO, SysRoleResourceEntity.class);
        sysRoleResourceDAO.save(entity);
        return EcBaseServiceResult.newInstanceOfSucResult(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EcBaseServiceResult updateSysRoleResource(SysRoleResourceDTO roleDTO) {
        SysRoleResourceEntity sysRoleResourceEntity = sysRoleResourceDAO.findById(roleDTO.getId());
        EcAssert.verifyDataNotExistent(sysRoleResourceEntity, "sysRoleResourceEntity");
        sysRoleResourceEntity = EcBaseUtils.copeFromObjToTargetObj(roleDTO, sysRoleResourceEntity);
        sysRoleResourceDAO.update(sysRoleResourceEntity);
        return EcBaseServiceResult.newInstanceOfSucResult(sysRoleResourceEntity);
    }
}

package com.easy.cloud.core.operator.sysresource.service.impl;

import com.easy.cloud.core.basic.constant.EcBaseConstant;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.operator.sysresource.dao.SysResourceDAO;
import com.easy.cloud.core.operator.sysresource.pojo.dto.SysResourceDTO;
import com.easy.cloud.core.operator.sysresource.pojo.entity.SysResourceEntity;
import com.easy.cloud.core.operator.sysresource.pojo.query.SysResourceQuery;
import com.easy.cloud.core.operator.sysresource.service.SysResourceService;
import com.easy.cloud.core.operator.sysrole.pojo.dto.SysRoleDTO;
import com.easy.cloud.core.operator.sysrole.service.SysRoleService;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 描述：服务实现类
 *
 * @author THINK
 * @date 2018-05-30 16:24:17
 */
@Service(value = "sysResourceService")
@EcLogAnnotation(logSwitch = false, analysisSwitch = false)
public class SysResourceServiceImpl extends EcBaseService implements SysResourceService {
    /**
     * 数据处理接口
     */
    @Autowired
    private SysResourceDAO sysResourceDAO;
    @Autowired
    private SysRoleService sysRoleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public EcBaseServiceResult save(SysResourceDTO resourceDTO) {
        SysResourceEntity resourceEntity = EcJSONUtils.parseObject(resourceDTO, SysResourceEntity.class);
        sysResourceDAO.save(resourceEntity);
        return EcBaseServiceResult.newInstanceOfSucResult(resourceEntity);
    }

    @Override
    public List<SysResourceDTO> findByRoleNos(List<Integer> roleNos) {
        if (EcCollectionsUtils.isEmpty(roleNos)) {
            return new ArrayList<>();
        }
        SysResourceQuery query = new SysResourceQuery();
        query.setRoleNos(roleNos);
        List<SysResourceEntity> sysRoleEntities = sysResourceDAO.listRoleResourceByQuery(query);
        if (EcCollectionsUtils.isEmpty(sysRoleEntities)) {
            return new ArrayList<>();
        }
        return EcJSONUtils.parseArray(sysRoleEntities, SysResourceDTO.class);
    }

    @Override
    public EcBaseServiceResult listPermissionOfCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        SysUserDTO sysUserDTO = (SysUserDTO) subject.getPrincipal();
        List<SysRoleDTO> roles = sysRoleService.findByUserId(sysUserDTO.getId());
        Set<Integer> roleNos = new HashSet<>();
        for (SysRoleDTO roleDTO : roles) {
            roleNos.add(roleDTO.getRoleNo());
        }
        List<SysResourceDTO> resourceDTOS = findByRoleNos(new ArrayList<>(roleNos));
        return EcBaseServiceResult.newInstanceOfSucResult(resourceDTOS);
    }

    @Override
    public EcBaseServiceResult getPermissionTree() {
        SysResourceQuery query = new SysResourceQuery();
        query.setAvailable(EcBaseConstant.EcAvailableEnum.YES.type());
        List<SysResourceEntity> resourceEntities = sysResourceDAO.listByQuery(query);
        List<SysResourceDTO> resourceDTOS = EcJSONUtils.parseArray(resourceEntities, SysResourceDTO.class);
        for (SysResourceDTO sysResourceDTO : resourceDTOS) {
            for (SysResourceDTO sysResourceDTOTemp : resourceDTOS) {
                if (EcBaseUtils.equals(sysResourceDTOTemp.getParentNo(), sysResourceDTO.getResourceNo())) {
                    sysResourceDTO.getChildren().add(sysResourceDTOTemp);
                }
            }
        }
        for (SysResourceDTO sysResourceDTO : resourceDTOS) {
            if (sysResourceDTO.getParentNo() == 0) {
                return EcBaseServiceResult.newInstanceOfSucResult(sysResourceDTO);
            }
        }
        return EcBaseServiceResult.newInstanceOfSuccess();
    }

    private List<SysResourceEntity> getInitData() {
        List<SysResourceEntity> sysResourceEntities = new ArrayList<>();
//		Integer resourceNo, String name, String type, String url, Integer parentNo,String parentNos, String permission
        sysResourceEntities.add(new SysResourceEntity(1, "资源", "menu", "", 0, ""));

        sysResourceEntities.add(new SysResourceEntity(11, "组织机构管理", "menu", "/organization", 1, "organization:*"));
        sysResourceEntities.add(new SysResourceEntity(12, "组织机构新增", "button", "", 11, "organization:create"));
        sysResourceEntities.add(new SysResourceEntity(13, "组织机构修改", "button", "", 11, "organization:update"));
        sysResourceEntities.add(new SysResourceEntity(14, "组织机构删除", "button", "", 11, "organization:delete"));
        sysResourceEntities.add(new SysResourceEntity(15, "组织机构查看", "button", "", 11, "organization:view"));

        sysResourceEntities.add(new SysResourceEntity(21, "用户管理", "menu", "/user", 1, "user:*"));
        sysResourceEntities.add(new SysResourceEntity(22, "用户新增", "button", "", 21, "user:create"));
        sysResourceEntities.add(new SysResourceEntity(23, "用户修改", "button", "", 21, "user:update"));
        sysResourceEntities.add(new SysResourceEntity(24, "用户删除", "button", "", 21, "user:delete"));
        sysResourceEntities.add(new SysResourceEntity(25, "用户查看", "button", "", 21, "user:view"));

        sysResourceEntities.add(new SysResourceEntity(31, "资源管理", "menu", "/resource", 1, "resource:*"));
        sysResourceEntities.add(new SysResourceEntity(32, "资源新增", "button", "", 31, "resource:create"));
        sysResourceEntities.add(new SysResourceEntity(33, "资源修改", "button", "", 31, "resource:update"));
        sysResourceEntities.add(new SysResourceEntity(34, "资源删除", "button", "", 31, "resource:delete"));
        sysResourceEntities.add(new SysResourceEntity(35, "资源查看", "button", "", 31, "resource:view"));

        sysResourceEntities.add(new SysResourceEntity(41, "角色管理", "menu", "/role", 1, "role:*"));
        sysResourceEntities.add(new SysResourceEntity(42, "角色新增", "button", "", 41, "role:create"));
        sysResourceEntities.add(new SysResourceEntity(43, "角色修改", "button", "", 41, "role:update"));
        sysResourceEntities.add(new SysResourceEntity(44, "角色删除", "button", "", 41, "role:delete"));
        sysResourceEntities.add(new SysResourceEntity(45, "角色查看", "button", "", 41, "role:view"));

        return sysResourceEntities;
    }

}

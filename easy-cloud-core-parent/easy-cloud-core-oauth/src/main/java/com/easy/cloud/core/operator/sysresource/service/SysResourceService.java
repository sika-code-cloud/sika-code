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
    /**
     * <p>
     * 保存资源信息
     * </p>
     *
     * @param resourceDTO
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult
     * @author daiqi
     * @date 2018/6/25 15:11
     */
    EcBaseServiceResult save(SysResourceDTO resourceDTO);

    /**
     * <p>
     * 获取当前用户的权限列表
     * </p>
     *
     * @param roleNos
     * @return java.util.List<com.easy.cloud.core.operator.sysresource.pojo.dto.SysResourceDTO>
     * @author daiqi
     * @date 2018/6/25 15:11
     */
    List<SysResourceDTO> findByRoleNos(List<Integer> roleNos);

    EcBaseServiceResult listPermissionOfCurrentUser();

    EcBaseServiceResult getPermissionTree();
}

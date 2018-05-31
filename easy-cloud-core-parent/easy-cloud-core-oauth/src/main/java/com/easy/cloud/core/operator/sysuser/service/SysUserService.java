package com.easy.cloud.core.operator.sysuser.service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;

/**
 * 描述：服务接口
 * 
 * @author THINK
 * @date 2018-05-30 16:23:53
 */
public interface SysUserService {
	public EcBaseServiceResult register(SysUserDTO sysUserDTO);

	public EcBaseServiceResult login(SysUserDTO sysUserDTO);

	public SysUserDTO findByUsernameAndPassword(String username, String password);

	public SysUserDTO findByPhoneAndPassword(String phone, String password);

	public SysUserDTO findByPhone(String phone);

	public SysUserDTO findByUsername(String username);

}

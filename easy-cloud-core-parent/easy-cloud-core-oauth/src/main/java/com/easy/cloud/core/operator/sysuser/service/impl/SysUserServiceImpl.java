package com.easy.cloud.core.operator.sysuser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.operator.sysuser.dao.SysUserDAO;
import com.easy.cloud.core.operator.sysuser.pojo.entity.SysUserEntity;
import com.easy.cloud.core.operator.sysuser.pojo.query.SysUserQuery;
import com.easy.cloud.core.operator.sysuser.service.SysUserService;

/**
 * 描述：服务实现类
 * 
 * @author THINK
 * @date 2018-05-30 16:23:53
 */
@Service(value = "sysUserService")
public class SysUserServiceImpl extends EcBaseService implements SysUserService {
	/** null数据处理接口 */
	@Autowired
	private SysUserDAO sysUserDAO;

	public SysUserEntity findByUsernameAndPassword(String username, String password) {
		if (EcStringUtils.isEmpty(username)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
		}
		if (EcStringUtils.isEmpty(password)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
		}

		SysUserQuery query = new SysUserQuery();
		query.setUsername(username);
		query.setPassword(password);
		List<SysUserEntity> userEntitys = sysUserDAO.listByQuery(query);
		if (EcCollectionsUtils.isEmpty(userEntitys)) {
			throw new RuntimeException("用户不存在");
		}
		return userEntitys.get(0);
	}

	public SysUserEntity findByPhoneAndPassword(String phone, String password) {
		if (EcStringUtils.isEmpty(phone)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
		}
		if (EcStringUtils.isEmpty(password)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
		}
		SysUserQuery query = new SysUserQuery();
		query.setUsername(phone);
		query.setPassword(password);
		List<SysUserEntity> userEntitys = sysUserDAO.listByQuery(query);
		if (EcCollectionsUtils.isEmpty(userEntitys)) {
			throw new RuntimeException("用户不存在");
		}
		return userEntitys.get(0);
	}

	public SysUserEntity findByPhone(String phone) {
		if (EcStringUtils.isEmpty(phone)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
		}
		
		SysUserQuery query = new SysUserQuery();
		query.setUsername(phone);
		if (EcStringUtils.isEmpty(query.getPhone())) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
		}
		List<SysUserEntity> userEntitys = sysUserDAO.listByQuery(query);
		if (EcCollectionsUtils.isEmpty(userEntitys)) {
			throw new RuntimeException("用户不存在");
		}
		return userEntitys.get(0);
	}

	public SysUserEntity findByUsername(String username) {
		if (EcStringUtils.isEmpty(username)) {
			throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
		}
		
		SysUserQuery query = new SysUserQuery();
		query.setUsername(username);
		List<SysUserEntity> userEntitys = sysUserDAO.listByQuery(query);
		if (EcCollectionsUtils.isEmpty(userEntitys)) {
			throw new RuntimeException("用户不存在");
		}
		return userEntitys.get(0);
	}
}

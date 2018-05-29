package com.easy.cloud.core.authority.service;

import com.easy.cloud.core.authority.pojo.UserInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
public interface UserInfoService {
	UserInfo findByUserName(String userName);
}

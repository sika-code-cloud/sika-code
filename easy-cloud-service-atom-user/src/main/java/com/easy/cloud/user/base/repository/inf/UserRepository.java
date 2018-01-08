package com.easy.cloud.user.base.repository.inf;

import com.easy.cloud.user.base.entity.UserEntity;

public interface UserRepository {
	UserEntity saveUserInfo(UserEntity userEntity);
	UserEntity findUserById(Long id);
}

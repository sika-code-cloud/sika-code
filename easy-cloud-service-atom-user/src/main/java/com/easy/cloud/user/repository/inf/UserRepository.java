package com.easy.cloud.user.repository.inf;

import com.easy.cloud.user.vo.UserVo;

public interface UserRepository {
	UserVo findUserById(Long id);
}
